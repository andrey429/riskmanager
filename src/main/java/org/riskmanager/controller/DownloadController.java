package org.riskmanager.controller;

import org.riskmanager.reports.RiskEvaluationReportBuilder;
import org.riskmanager.service.AssetService;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 21.10.12
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/report")
public class DownloadController {

    @Resource
    AssetService assetService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getFile(

            HttpServletResponse response) {
        try {
            // get your file as InputStream

            RiskEvaluationReportBuilder riskEvaluationReportBuilder = new RiskEvaluationReportBuilder();

            String reportName = "report.docx";
            riskEvaluationReportBuilder.exportAssetsToDocxFile(assetService.getAll(), null, reportName);

            //open file, open input stream
            File reportFile = new File(reportName);
            InputStream reportInputStream = new FileInputStream(reportFile);
            //set response header to download file

            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-Disposition", "attachment; filename = \"" + reportName + "\"");

            //copy to response output stream
            FileCopyUtils.copy(reportInputStream, response.getOutputStream());
            response.flushBuffer();

            //close stream, delete file
            reportInputStream.close();
            reportFile.delete();


        } catch (IOException ex) {

            throw new RuntimeException("IOError writing file to output stream");
        }

        return null;

    }

}
