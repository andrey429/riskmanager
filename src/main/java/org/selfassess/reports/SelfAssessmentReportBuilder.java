package org.selfassess.reports;

import org.apache.log4j.Logger;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.StyleDefinitionsPart;
import org.docx4j.wml.*;
import org.riskmanager.domain.Person;
import org.selfassess.domain.SelfAssessmentModel;
import org.selfassess.fbo.EV1FormBackingObject;
import org.selfassess.utils.EV1ValueFactory;
import org.selfassess.utils.POJO2FBOConverter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 06.01.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */


public class SelfAssessmentReportBuilder extends TableGenerator {

    private Logger logger;


    private ReloadableResourceBundleMessageSource messageSource;
    private String[] columnWidths = {"1623", "4965", "1620", "3990", "1410", "1440"};
    private String pageWidth = "16838";
    private String pageHeight = "11906";
    private String tableFontSize = "16";
    private String headerTitleStyle = "Title";

    private String largeCellWidth;


    public SelfAssessmentReportBuilder(ReloadableResourceBundleMessageSource messageSource) {
        super();
        logger = Logger.getLogger("controller");
        this.messageSource = messageSource;
        largeCellWidth = new BigInteger(columnWidths[0]).add(new BigInteger(columnWidths[1]))
                .add(new BigInteger(columnWidths[2]))
                .add(new BigInteger(columnWidths[3]))
                .add(new BigInteger(columnWidths[4]))
                .toString();

    }

    protected String getResourceMessage(String resourceString) {
        return messageSource.getMessage(resourceString, null, Locale.getDefault());
    }

    public void exportSelfAssessmentToDocxFile(SelfAssessmentModel selfAssessment, Person reportGeneratingPerson,
                                               String saveToFileName) {

        EV1FormBackingObject ev1FBO = new POJO2FBOConverter().convertToFormBackingObject(selfAssessment.getEv1Model());
        try {

            WordprocessingMLPackage wordprocessingMLPackage = WordprocessingMLPackage.createPackage();

            if (messageSource == null) {
                logger.debug("message source null: not autowired?");
                return;
            }

            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText(headerTitleStyle,
                    getResourceMessage("report.title"));


            wordprocessingMLPackage.getMainDocumentPart().getContent().add(createBlankPage());
            //EV1
            addEV1Part(wordprocessingMLPackage, ev1FBO);


            //page orientation etc
            SectPr sectPr = objectFactory.createSectPr();
            SectPr.PgSz pgSz = objectFactory.createSectPrPgSz();

            pgSz.setOrient(STPageOrientation.LANDSCAPE);
            pgSz.setW(new BigInteger(pageWidth));
            pgSz.setH(new BigInteger(pageHeight));

            sectPr.setPgSz(pgSz);
            wordprocessingMLPackage.getMainDocumentPart().getJaxbElement().getBody().setSectPr(sectPr);
            //
            //save
            wordprocessingMLPackage.save(new File(saveToFileName));

        } catch (Exception e) {
            logger.debug("Error occured while generating/saving report");
            e.printStackTrace();
        }
    }


    protected void addEV1Part(WordprocessingMLPackage packMlPackage, EV1FormBackingObject ev1FBO) {
        //add title
        /*packMlPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle",
                getResourceMessage("report.EV1Title"));*/

        /*Tbl docTable = createTable();*/
        /*groupTable.getContent().addAll(tableHead);*/
        EV1ValueFactory ev1ValueFactory = new EV1ValueFactory();
        List<Tr> tableHead = getTableHead();


        //pushing groups to table


        for (int i = 0; i < 10; i++) {//group

            Tbl groupTable = createTable();//each group in its own table
            groupTable.getContent().addAll(tableHead);
            int count = ev1ValueFactory.getCounts()[i];
            List<Tr> groupOfRows = new ArrayList<Tr>();

            packMlPackage.getMainDocumentPart().addStyledParagraphOfText(headerTitleStyle,
                    getResourceMessage("report.group")
                            + " "
                            + "M" + (i + 1)
                            + " "
                            + "\""
                            + getResourceMessage("M" + (i + 1) + ".label")
                            + "\"");

            for (int j = 0; j < count; j++) {//parameters in group
                String column1Str = "M" + (i + 1) + "." + (j + 1);
                String column2Str = getResourceMessage("M" + (i + 1) + "." + (j + 1) + ".label");
                String column3Str;
                if (ev1ValueFactory.getRequirement()[i][j] == 1) {
                    column3Str = getResourceMessage("self.label.isRequired");
                } else {
                    column3Str = getResourceMessage("self.label.isNotRequired");
                }

                String column4Str;
                Double parameter = ev1FBO.getParameterValues().get(i).get(j);
                if (parameter.compareTo((-1.0)) == 0) {
                    column4Str = getResourceMessage("report.values.Value6");
                } else if (parameter.compareTo((0.0)) == 0) {
                    column4Str = getResourceMessage("report.values.Value1");

                } else if (parameter.compareTo((0.25)) == 0) {
                    column4Str = getResourceMessage("report.values.Value2");

                } else if (parameter.compareTo((0.5)) == 0) {
                    column4Str = getResourceMessage("report.values.Value3");

                } else if (parameter.compareTo((0.75)) == 0) {
                    column4Str = getResourceMessage("report.values.Value4");

                } else if (parameter.compareTo((1.0)) == 0) {
                    column4Str = getResourceMessage("report.values.Value5");

                } else {
                    column4Str = getResourceMessage("report.values.Injection");
                }


                String column5Str = Double.toString(ev1ValueFactory.getParameterWeights()[i][j]);
                String column6Str;


                Double recalculatedParameter = ev1FBO.getParameterWeightedRecalculatedValues().get(i).get(j);
                if (recalculatedParameter.compareTo(-1.0) == 0) {
                    column6Str = getResourceMessage("report.values.Value6");
                } else {
                    column6Str = recalculatedParameter.toString();
                }


                Tr row = createRow();
                addStyledTableCell(row, column1Str, false, tableFontSize, columnWidths[0]);
                addStyledTableCell(row, column2Str, false, tableFontSize, columnWidths[1]);
                addStyledTableCell(row, column3Str, false, tableFontSize, columnWidths[2]);
                addStyledTableCell(row, column4Str, false, tableFontSize, columnWidths[3]);
                addStyledTableCell(row, column5Str, false, tableFontSize, columnWidths[4]);
                addStyledTableCell(row, column6Str, false, tableFontSize, columnWidths[5]);

                groupOfRows.add(j, row);


            }

            groupTable.getContent().addAll(groupOfRows);

            //add final row
            //todo stub
            Tr groupFinalRow = objectFactory.createTr();

            Tc largeCell = objectFactory.createTc();
            TcPr tcPr = objectFactory.createTcPr();
            TcPrInner.GridSpan gridSpan = objectFactory.createTcPrInnerGridSpan();
            gridSpan.setVal(new BigInteger("5"));//for 5 cells
            tcPr.setGridSpan(gridSpan);
            tcPr.setGridSpan(gridSpan);
            largeCell.setTcPr(tcPr);

            addStyling(largeCell,
                    (getResourceMessage("report.finalValue") + " M" + (i + 1)),
                    true, tableFontSize, largeCellWidth
            );

            groupFinalRow.getContent().add(largeCell);
            addStyledTableCell(groupFinalRow, Double.toString(ev1FBO.getmGroupValues().get(i)),
                    true, tableFontSize, columnWidths[5]);


            //

            groupTable.getContent().add(groupFinalRow);
            addBorders(groupTable);
            packMlPackage.getMainDocumentPart().addObject(groupTable);
            packMlPackage.getMainDocumentPart().getContent().add(createBlankPage());
        }


        /*addBorders(docTable);
        packMlPackage.getMainDocumentPart().addObject(docTable);*/


    }


    protected List<Tr> getTableHead() {
        List<Tr> result = new ArrayList<Tr>();

        Tr tableHeadUpper = createRow();
        /*Tr tableHeadLower = createRow();*/


        //fill upper cell
        /*addStyledTableCell(tableHeadUpper, );*/
/*        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column1"), true, tableFontSize, columnWidths[0]));
        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column2"), true, tableFontSize, columnWidths[1]));
        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column3"), true, tableFontSize, columnWidths[2]));
        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column4"), true, tableFontSize, columnWidths[3]));
        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column5"), true, tableFontSize, columnWidths[4]));
        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column6"), true, tableFontSize, columnWidths[5]));*/

        addStyledTableCell(tableHeadUpper, getResourceMessage("report.column1"), true, tableFontSize, columnWidths[0]);
        addStyledTableCell(tableHeadUpper, getResourceMessage("report.column2"), true, tableFontSize, columnWidths[1]);
        addStyledTableCell(tableHeadUpper, getResourceMessage("report.column3"), true, tableFontSize, columnWidths[2]);
        addStyledTableCell(tableHeadUpper, getResourceMessage("report.column4"), true, tableFontSize, columnWidths[3]);
        addStyledTableCell(tableHeadUpper, getResourceMessage("report.column5"), true, tableFontSize, columnWidths[4]);
        addStyledTableCell(tableHeadUpper, getResourceMessage("report.column6"), true, tableFontSize, columnWidths[5]);


        //fill lower cell



        /*tableHeadLower.getContent().add(createTableCellVMerge("1623"));
        tableHeadLower.getContent().add(createTableCellVMerge("4965"));
        tableHeadLower.getContent().add(createTableCellVMerge("1620"));
        addStyledTableCell(tableHeadLower, getResourceMessage("report.columnLowerVal1"), true, tableFontSize, "665");
        addStyledTableCell(tableHeadLower, getResourceMessage("report.columnLowerVal2"), true, tableFontSize, "665");
        addStyledTableCell(tableHeadLower, getResourceMessage("report.columnLowerVal3"), true, tableFontSize, "665");
        addStyledTableCell(tableHeadLower, getResourceMessage("report.columnLowerVal4"), true, tableFontSize, "665");
        addStyledTableCell(tableHeadLower, getResourceMessage("report.columnLowerVal5"), true, tableFontSize, "665");
        addStyledTableCell(tableHeadLower, getResourceMessage("report.columnLowerVal6"), true, tableFontSize, "665");
        tableHeadLower.getContent().add(createTableCellVMerge("1410"));
        tableHeadLower.getContent().add(createTableCellVMerge("1440"));*/


        result.add(0, tableHeadUpper);
        /*result.add(1, tableHeadLower);*/


        return result;
    }


}

