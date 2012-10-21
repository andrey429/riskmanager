package org.riskmanager.reports;

import org.apache.log4j.Logger;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;
import org.riskmanager.domain.Asset;
import org.riskmanager.domain.AssetQuery;
import org.riskmanager.domain.Organisation;
import org.riskmanager.domain.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 21.10.12
 * Time: 9:17
 * To change this template use File | Settings | File Templates.
 */
public class RiskEvaluationReportBuilder {

    protected Logger logger = Logger.getLogger("service");
    private ObjectFactory objectFactory;

    public void exportAssetsToDocxFile(List<Asset> assetsList, Person reportGeneratingPerson,
                                       String saveToFileName) {
        try {
            WordprocessingMLPackage wordprocessingMLPackage = WordprocessingMLPackage.createPackage();

            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title",
                    getUTF8EncodedString("Область оценки рисков"));


            addOrganisationsPart(wordprocessingMLPackage, assetsList);
            addPersonsPart(wordprocessingMLPackage, assetsList);
            addAssetsPart(wordprocessingMLPackage, assetsList);

            wordprocessingMLPackage.save(new File(saveToFileName));
        } catch (Exception e) {
            logger.debug("Error occured while generating/saving report");
            e.printStackTrace();
        }
    }

    public void addOrganisationsPart(WordprocessingMLPackage wordprocessingMLPackage, List<Asset> assetList) {
        wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle",
                getUTF8EncodedString("1. Организации"));

        List<Organisation> organisations = getAllUniqueOrganisationsFromAssetList(assetList);
        objectFactory = Context.getWmlObjectFactory();
        Tbl organisationsTable = objectFactory.createTbl();
        Tr tableHead = objectFactory.createTr();

        addTableHeaders(tableHead, wordprocessingMLPackage, "Название", "Адрес");
        organisationsTable.getContent().add(tableHead);

        for (Organisation organisation : organisations) {
            Tr assetDescriptionRow = objectFactory.createTr();
            addTableCell(assetDescriptionRow, wordprocessingMLPackage, organisation.getOrganisationName());
            addTableCell(assetDescriptionRow, wordprocessingMLPackage, organisation.getOrganisationAddress());

            organisationsTable.getContent().add(assetDescriptionRow);
        }
        addBorders(organisationsTable);
        wordprocessingMLPackage.getMainDocumentPart().addObject(organisationsTable);
    }

    public void addPersonsPart(WordprocessingMLPackage wordprocessingMLPackage, List<Asset> assetList) {

        wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle",
                getUTF8EncodedString("2. Рабочая группа"));

        List<Person> persons = getAllUniquePersonsFromAssetsList(assetList);

        objectFactory = Context.getWmlObjectFactory();
        Tbl personsTable = objectFactory.createTbl();
        Tr tableHead = objectFactory.createTr();

        addTableHeaders(tableHead, wordprocessingMLPackage, "Фамилия", "Имя", "Отчество", "Организация",
                "Структурное подразделение", "Должность");
        personsTable.getContent().add(tableHead);

        for (Person person : persons) {
            Tr assetDescriptionRow = objectFactory.createTr();
            addTableCell(assetDescriptionRow, wordprocessingMLPackage, person.getLastName());
            addTableCell(assetDescriptionRow, wordprocessingMLPackage, person.getFirstName());
            addTableCell(assetDescriptionRow, wordprocessingMLPackage, person.getSecondName());
            addTableCell(assetDescriptionRow, wordprocessingMLPackage, person.getOrganisation().getOrganisationName());
            addTableCell(assetDescriptionRow, wordprocessingMLPackage, person.getDepartment());
            addTableCell(assetDescriptionRow, wordprocessingMLPackage, person.getJobPosition());
            personsTable.getContent().add(assetDescriptionRow);
        }
        addBorders(personsTable);
        wordprocessingMLPackage.getMainDocumentPart().addObject(personsTable);
    }


    public void addAssetsPart(WordprocessingMLPackage packMlPackage, List<Asset> assets) {
        packMlPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle",
                getUTF8EncodedString("3. Активы"));

        objectFactory = Context.getWmlObjectFactory();


        Tbl assetsTable = objectFactory.createTbl();

        Tr tableHead = objectFactory.createTr();
        addTableHeaders(tableHead, packMlPackage, "Название", "Описание", "Владелец",
                "Конфиденциальность", "Целостность", "Доступность", "Расположение");

        assetsTable.getContent().add(tableHead);
        for (Asset asset : assets) {
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

        addBorders(assetsTable);
        packMlPackage.getMainDocumentPart().addObject(assetsTable);


    }


    private List<Organisation> getAllUniqueOrganisationsFromAssetList(List<Asset> assetList) {

        HashSet<Organisation> organisationHashSet = new HashSet<Organisation>();
        for (Asset asset : assetList) {
            organisationHashSet.add(asset.getPersonOwner().getOrganisation());
        }
        return new ArrayList<Organisation>(organisationHashSet);
    }

    private List<Person> getAllUniquePersonsFromAssetsList(List<Asset> assetList) {
        HashSet<Person> personHashSet = new HashSet<Person>();

        for (Asset asset : assetList) {
            personHashSet.add(asset.getPersonOwner());
        }
        return new ArrayList<Person>(personHashSet);
    }


    private void addTableCell(Tr tableRow, WordprocessingMLPackage wordMLPackage, String content) {
        Tc tableCell = objectFactory.createTc();
        tableCell.getContent().add(
                wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
        tableRow.getContent().add(tableCell);

    }

    private static void addBorders(Tbl table) {
        table.setTblPr(new TblPr());
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);
        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);
        table.getTblPr().setTblBorders(borders);

    }

    private String getUTF8EncodedString(String input) {
        return new String(input.getBytes(), Charset.forName("UTF-8"));
    }

    private void addTableHeaders(Tr tableHead, WordprocessingMLPackage packMlPackage, String... headers) {
        for (String header : headers) {
            //addTableCell(tableHead, packMlPackage, getUTF8EncodedString(header));
            addStyledTableCell(tableHead, getUTF8EncodedString(header), true, "24");
        }
    }

    private void addStyledTableCell(Tr tableRow, String content,
                                    boolean bold, String fontSize) {
        Tc tableCell = objectFactory.createTc();
        addStyling(tableCell, content, bold, fontSize);
        tableRow.getContent().add(tableCell);

    }

    private void addStyling(Tc tableCell, String content,
                            boolean bold, String fontSize) {
        P paragraph = objectFactory.createP();
        Text text = objectFactory.createText();
        text.setValue(content);
        R run = objectFactory.createR();
        run.getContent().add(text);
        paragraph.getContent().add(run);
        RPr runProperties = objectFactory.createRPr();
        if (bold) {
            addBoldStyle(runProperties);
        }
        if (fontSize != null && !fontSize.isEmpty()) {
            setFontSize(runProperties, fontSize);
        }
        run.setRPr(runProperties);
        tableCell.getContent().add(paragraph);

    }

    private void setFontSize(RPr runProperties, String fontSize) {
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(fontSize));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
    }

    private static void addBoldStyle(RPr runProperties) {
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        b.setVal(true);
        runProperties.setB(b);
    }
}
