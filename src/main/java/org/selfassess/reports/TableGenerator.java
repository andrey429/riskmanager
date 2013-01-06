package org.selfassess.reports;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;

import java.math.BigInteger;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 06.01.13
 * Time: 18:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class TableGenerator {

    private ObjectFactory objectFactory;


    public TableGenerator() {
        this.objectFactory = Context.getWmlObjectFactory();
    }

    protected static void addBorders(Tbl table) {
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

    protected void addTableCell(Tr tableRow, WordprocessingMLPackage wordMLPackage, String content) {
        Tc tableCell = objectFactory.createTc();
        tableCell.getContent().add(
                wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
        tableRow.getContent().add(tableCell);

    }

    protected void addTableHeaders(Tr tableHead,  String... headers) {
        for (String header : headers) {
            //addTableCell(tableHead, packMlPackage, getUTF8EncodedString(header));
            addStyledTableCell(tableHead, getUTF8EncodedString(header), true, "24");
        }
    }

    protected void addStyling(Tc tableCell, String content,
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

    protected void setFontSize(RPr runProperties, String fontSize) {
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(fontSize));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
    }

    protected static void addBoldStyle(RPr runProperties) {
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        b.setVal(true);
        runProperties.setB(b);
    }
    protected void addStyledTableCell(Tr tableRow, String content,
                                    boolean bold, String fontSize) {
        Tc tableCell = objectFactory.createTc();
        addStyling(tableCell, getUTF8EncodedString(content), bold, fontSize);
        tableRow.getContent().add(tableCell);

    }

    protected Tbl createTable(){
        return objectFactory.createTbl();
    }

    protected Tr createRow(){
        return objectFactory.createTr();
    }
    protected Tc createCell(){
        return objectFactory.createTc();
    }


    protected String getUTF8EncodedString(String input) {
        return new String(input.getBytes(), Charset.forName("UTF-8"));
    }


}
