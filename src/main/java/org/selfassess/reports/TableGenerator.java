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

    protected ObjectFactory objectFactory;


    public TableGenerator() {
        this.objectFactory = Context.getWmlObjectFactory();
    }

    protected P createBlankPage() {
        P paragraph = objectFactory.createP();
        R run = objectFactory.createR();
        Br pageBreak = objectFactory.createBr();
        pageBreak.setType(STBrType.PAGE);
        run.getContent().add(pageBreak);
        paragraph.getContent().add(run);

        return paragraph;
    }

    protected  void addBorders(Tbl table) {
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

    /*protected void addTableCell(Tr tableRow, WordprocessingMLPackage wordMLPackage, String content) {
        Tc tableCell = objectFactory.createTc();
        tableCell.getContent().add(
                wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
        tableRow.getContent().add(tableCell);

    }*/

    /*protected void addTableHeaders(Tr tableHead, String... headers) {
        for (String header : headers) {
            //addTableCell(tableHead, packMlPackage, getUTF8EncodedString(header));
            addStyledTableCell(tableHead, getUTF8EncodedString(header), true, "24");
        }
    }*/

    protected void addStyling(Tc tableCell, String content,
                              boolean bold, String fontSize, String cellWidth) {
        P paragraph = objectFactory.createP();
        Text text = objectFactory.createText();
        text.setValue(/*getUTF8EncodedString(*/content/*)*/);
        R run = objectFactory.createR();
        run.getContent().add(text);
        paragraph.getContent().add(run);
        RPr runProperties = objectFactory.createRPr();


        CTLanguage lang = objectFactory.createCTLanguage();
        lang.setVal("ru-RU");
        runProperties.setLang(lang);

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

    protected  void addBoldStyle(RPr runProperties) {
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        b.setVal(true);
        runProperties.setB(b);
    }

    protected Tc addStyledTableCell(Tr tableRow, String content,
                                    boolean bold, String fontSize, String cellWidth) {
        Tc tableCell = objectFactory.createTc();
        addStyling(tableCell, /*getUTF8EncodedString(*/content/*)*/, bold, fontSize, cellWidth);

        TcPr tcpr = objectFactory.createTcPr();

        TblWidth tblWidth = objectFactory.createTblWidth();
        tblWidth.setType("dxa");
        tblWidth.setW(new BigInteger(cellWidth));
        tcpr.setTcW(tblWidth);
        CTVerticalJc vAlign = objectFactory.createCTVerticalJc();
        vAlign.setVal(STVerticalJc.CENTER);
        tcpr.setVAlign(vAlign);


        tableCell.setTcPr(tcpr);
        tableRow.getContent().add(tableCell);

        return tableCell;//todo ?remove

    }

    protected Tbl createTable() {
        return objectFactory.createTbl();
    }

    protected Tr createRow() {
        return objectFactory.createTr();
    }

    protected Tc createCell() {
        return objectFactory.createTc();
    }


    protected P createParagraph() {
        P p = objectFactory.createP();

        R run = objectFactory.createR();
        p.getContent().add(run);
        RPr runProperties = objectFactory.createRPr();
        CTLanguage lang = objectFactory.createCTLanguage();
        lang.setVal("ru-RU");
        runProperties.setLang(lang);

        run.setRPr(runProperties);

        return p;
    }


    public Tc createTableCellVMerge(String cellWidth) {
        org.docx4j.wml.Tc tc = objectFactory.createTc();
        org.docx4j.wml.TcPr tcpr = objectFactory.createTcPr();
        tc.setTcPr(tcpr);
        CTVerticalJc valign = objectFactory.createCTVerticalJc();
        valign.setVal(STVerticalJc.TOP);
        tcpr.setVAlign(valign);


        //
        TblWidth tblWidth = objectFactory.createTblWidth();
        tblWidth.setW(new BigInteger(cellWidth));
        tcpr.setTcW(tblWidth);
        //


        org.docx4j.wml.TcPrInner.VMerge vm = objectFactory.createTcPrInnerVMerge();
        /*vm.setVal("restart);*/
        tcpr.setVMerge(vm);
        P p = createParagraph();
        tc.getContent().add(p);
        return tc;
    }

    public Tc createTableCellVMerge(String textString, boolean bold, String fontSize, String cellWidth) {
        org.docx4j.wml.Tc tc = objectFactory.createTc();
        org.docx4j.wml.TcPr tcpr = objectFactory.createTcPr();
        tc.setTcPr(tcpr);
        //width
        TblWidth tblWidth = objectFactory.createTblWidth();
        tblWidth.setW(new BigInteger(cellWidth));
        tcpr.setTcW(tblWidth);
        //
        CTVerticalJc valign = objectFactory.createCTVerticalJc();
        valign.setVal(STVerticalJc.TOP);
        tcpr.setVAlign(valign);

        org.docx4j.wml.TcPrInner.VMerge vm = objectFactory.createTcPrInnerVMerge();
        vm.setVal("restart");
        tcpr.setVMerge(vm);
        //
        P paragraph = createParagraph();

        Text text = objectFactory.createText();
        text.setValue(/*getUTF8EncodedString(*/textString/*)*/);
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
        //


        tc.getContent().add(paragraph);


        return tc;
    }


}
