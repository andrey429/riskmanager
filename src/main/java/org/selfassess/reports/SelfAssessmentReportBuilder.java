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
    private String subtitle = "Subtitle";

    private String largeCellWidth;
    private String littleCellWidth;


    public SelfAssessmentReportBuilder(ReloadableResourceBundleMessageSource messageSource) {
        super();
        logger = Logger.getLogger("controller");
        this.messageSource = messageSource;
        largeCellWidth = new BigInteger(columnWidths[0])
                .add(new BigInteger(columnWidths[1]))
                .add(new BigInteger(columnWidths[2]))
                .add(new BigInteger(columnWidths[3]))
                .add(new BigInteger(columnWidths[4]))
                .toString();
        littleCellWidth = new BigInteger(columnWidths[3]).divide(new BigInteger("6")).toString();
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

            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText(subtitle,
                    getResourceMessage("self.newAssessment.name") + ": "
                            + (selfAssessment.getSelfAssessmentName() == null ?
                            getResourceMessage("report.values.NULL") :
                            selfAssessment.getSelfAssessmentName())
            );

            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText(subtitle,
                    getResourceMessage("self.newAssessment.description") + ": "
                            + (selfAssessment.getDescription() == null ?
                            getResourceMessage("report.values.NULL") :
                            selfAssessment.getDescription())
            );

            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText(subtitle,
                    getResourceMessage("self.newAssessment.auditor") + ": "
                            + (selfAssessment.getAuditors() == null ?
                            getResourceMessage("report.values.NULL") :
                            selfAssessment.getAuditors())
            );
            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText(subtitle,
                    getResourceMessage("self.newAssessment.author") + ": "
                            + (selfAssessment.getCreator() == null ?
                            getResourceMessage("report.values.NULL") :
                            selfAssessment.getCreator())
            );


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


                Double parameter = ev1FBO.getParameterValues().get(i).get(j);

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
                /*addStyledTableCell(row, column4Str, false, tableFontSize, columnWidths[3]);*/

                row.getContent().addAll(getGroupOfFilledValueCellsBasedOnDoubleValue(parameter));


                addStyledTableCell(row, column5Str, false, tableFontSize, columnWidths[4]);
                addStyledTableCell(row, column6Str, false, tableFontSize, columnWidths[5]);

                groupOfRows.add(j, row);


            }

            groupTable.getContent().addAll(groupOfRows);

            //add final row
            Tr groupFinalRow = objectFactory.createTr();

            Tc largeCell = objectFactory.createTc();
            TcPr tcPr = objectFactory.createTcPr();
            TcPrInner.GridSpan gridSpan = objectFactory.createTcPrInnerGridSpan();
            gridSpan.setVal(new BigInteger("10"));//for 10 cells
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

            groupTable.getContent().add(groupFinalRow);
            addBorders(groupTable);
            packMlPackage.getMainDocumentPart().addObject(groupTable);
            packMlPackage.getMainDocumentPart().getContent().add(createBlankPage());
        }

    }


    protected List<Tc> getGroupOfFilledValueCellsBasedOnDoubleValue(Double value) {

        Double[] values = {0.0, 0.25, 0.5, 0.75, 1.0, -1.0};
        List<Tc> resultList = new ArrayList<Tc>();

        for (int i = 0; i < 6; i++) {
            Tc tableCell = objectFactory.createTc();
            String content = value.compareTo(values[i]) == 0 ? "+" : "-";

            addStyling(tableCell, content, true, tableFontSize, littleCellWidth);

            TcPr tcpr = objectFactory.createTcPr();

            TblWidth tblWidth = objectFactory.createTblWidth();
            tblWidth.setType("dxa");
            tblWidth.setW(new BigInteger(littleCellWidth));
            tcpr.setTcW(tblWidth);

            CTVerticalJc vAlign = objectFactory.createCTVerticalJc();
            vAlign.setVal(STVerticalJc.CENTER);
            tcpr.setVAlign(vAlign);


            tableCell.setTcPr(tcpr);
            resultList.add(tableCell);


        }

        return resultList;
    }


    protected List<Tr> getTableHead() {
        List<Tr> result = new ArrayList<Tr>();

        Tr tableHeadUpper = createRow();
        Tr tableHeadLower = createRow();

        TcPrInner.GridSpan gridSpan = objectFactory.createTcPrInnerGridSpan();
        gridSpan.setVal(new BigInteger("6"));

        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column1"), true, tableFontSize, columnWidths[0]));
        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column2"), true, tableFontSize, columnWidths[1]));
        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column3"), true, tableFontSize, columnWidths[2]));

        Tc large = (addStyledTableCell(tableHeadUpper, getResourceMessage("report.column4"), true, tableFontSize, columnWidths[3]));
        TcPr largeTcPr = objectFactory.createTcPr();
        largeTcPr.setGridSpan(gridSpan);
        large.setTcPr(largeTcPr);
        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column4"), true, tableFontSize, columnWidths[4]));
        tableHeadUpper.getContent().add(createTableCellVMerge(getResourceMessage("report.column5"), true, tableFontSize, columnWidths[5]));

        tableHeadLower.getContent().add(createTableCellVMerge(columnWidths[0]));
        tableHeadLower.getContent().add(createTableCellVMerge(columnWidths[1]));
        tableHeadLower.getContent().add(createTableCellVMerge(columnWidths[2]));

        addStyledTableCell(tableHeadLower, getResourceMessage("report.values.Value1"), true, tableFontSize, littleCellWidth);
        addStyledTableCell(tableHeadLower, getResourceMessage("report.values.Value2"), true, tableFontSize, littleCellWidth);
        addStyledTableCell(tableHeadLower, getResourceMessage("report.values.Value3"), true, tableFontSize, littleCellWidth);
        addStyledTableCell(tableHeadLower, getResourceMessage("report.values.Value4"), true, tableFontSize, littleCellWidth);
        addStyledTableCell(tableHeadLower, getResourceMessage("report.values.Value5"), true, tableFontSize, littleCellWidth);
        addStyledTableCell(tableHeadLower, getResourceMessage("report.values.Value6"), true, tableFontSize, littleCellWidth);


        tableHeadLower.getContent().add(createTableCellVMerge(columnWidths[4]));
        tableHeadLower.getContent().add(createTableCellVMerge(columnWidths[5]));

        result.add(0, tableHeadUpper);
        result.add(1, tableHeadLower);

        return result;
    }


}

