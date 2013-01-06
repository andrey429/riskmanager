package org.selfassess.reports;

import org.apache.log4j.Logger;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;
import org.riskmanager.domain.Asset;
import org.riskmanager.domain.Person;
import org.selfassess.domain.SelfAssessmentModel;
import org.selfassess.fbo.EV1FormBackingObject;
import org.selfassess.utils.POJO2FBOConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 06.01.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class SelfAssessmentReportBuilder extends TableGenerator {

    private Logger logger;


    public SelfAssessmentReportBuilder() {
        super();
        logger = Logger.getLogger("debug");
    }

    public void exportSelfAssessmentToDocxFile(SelfAssessmentModel selfAssessment, Person reportGeneratingPerson,
                                               String saveToFileName) {

        EV1FormBackingObject ev1FBO = new POJO2FBOConverter().convertToFormBackingObject(selfAssessment.getEv1Model());
        try {

            WordprocessingMLPackage wordprocessingMLPackage = WordprocessingMLPackage.createPackage();

            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title",
                    getUTF8EncodedString("Самооценка по требованиям СТО БР ИББС-1.0"));
            addEV1Part(wordprocessingMLPackage, ev1FBO);
            wordprocessingMLPackage.save(new File(saveToFileName));

        } catch (Exception e) {
            logger.debug("Error occured while generating/saving report");
            e.printStackTrace();
        }
    }


    protected void addEV1Part(WordprocessingMLPackage packMlPackage, EV1FormBackingObject ev1FBO) {
        packMlPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle",
                getUTF8EncodedString("Текущее состояние информационной безопасности организации"));


        Tbl docTable = createTable();


        docTable.getContent().add(tableHead);
        /*for (Asset asset : assets) {
            Tr assetDescriptionRow = objectFactory.createTr();
            addTableCell(assetDescriptionRow, packMlPackage, asset.getName());
            addTableCell(assetDescriptionRow, packMlPackage, asset.getDescription());
            addTableCell(assetDescriptionRow, packMlPackage, asset.getPersonOwner().toString());
            addTableCell(assetDescriptionRow, packMlPackage, (asset.getRequiresConfidentiality() ? "+" : " "));
            addTableCell(assetDescriptionRow, packMlPackage, (asset.getRequiresIntegrity() ? "+" : " "));
            addTableCell(assetDescriptionRow, packMlPackage, (asset.getRequiresAvailability() ? "+" : " "));
            addTableCell(assetDescriptionRow, packMlPackage, asset.getAssetLocation());
            assetsTable.getContent().add(assetDescriptionRow);
        }
*/

        addBorders(docTable);
        packMlPackage.getMainDocumentPart().addObject(docTable);


    }


    protected List<Tr> getTableHead() {
        List<Tr> result = new ArrayList<Tr>();

        Tr tableHeadUpper = createRow();
        Tr tableHeadLower = createRow();

         /*"Обозначение частного показателя ИБ",
                "Частный показатель ИБ", "Обязательность выполнения","Коэффициент значимости частного показателя ИБ",
                 "Вычисленное значение частного показателя ИБ"*/


        //fill upper cell
        addStyledTableCell(tableHeadUpper, "Обозначение частного показателя ИБ", true, "24");
        addStyledTableCell(tableHeadUpper, "Частный показатель ИБ", true, "24");
        Tc idxCell = createCell();
        addStyling(idxCell, ();
        Tc nameCell = createCell();
        addStyling(nameCell, ();
        Tc estimationCell = createCell();
        addStyling(estimationCell, ("Оценка частного показателя ИБ"), true, "24");
        Tc weightCell = createCell();
        addStyling(weightCell, ("Коэффициент значимости частного показателя ИБ"), true, "24");
        Tc finalValueCell = createCell();
        addStyling(finalValueCell, "Вычисленное значение частного показателя ИБ", true, "24");

        //fill lower cell



        tableHead.getContent().add(valueCell);

        //whooh, finish
        addTableHeaders(tableHead, );


    }


}
