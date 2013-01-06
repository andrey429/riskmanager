<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>

</title>

<script type='text/javascript'
        src='${pageContext.request.contextPath}/js/mootools-core-1.4.5-full-compat.js'></script>


<script type='text/javascript'>
    window.addEvent('load', function () {
        function prepareData(data, NUM_SECTORS) {
            var sectors = new Array(NUM_SECTORS);
            for (var i = 0; i < data.length; ++i) {
                sectors[data[i].parameter - 1] = { percent: data[i].percent, color: data[i].color };
            }
            return sectors;
        }

        function drawChart(data) {
            // Some "const" values
            var MARGIN = 40;
            var CHART_ORIENTATION = Math.PI / 2;
            var NUM_SECTORS = 34;
            var HALF_NUM_SECTORS = NUM_SECTORS >> 1;
            var SECTOR_SIZE = Math.PI / HALF_NUM_SECTORS;

            var canvas = document.getElementById('radar_chart');
            var ctx = canvas.getContext('2d');
            var width = canvas.width;
            var height = canvas.height;

            var size = width < height ? width : height;
            size -= MARGIN;

            var centerX, centerY;
            centerX = width >> 1;
            centerY = height >> 1;

            var radius = size >> 1;
            var radiusOffset = 5;

            var sectors = prepareData(data, NUM_SECTORS);

            // Draw the filled sectors (parameters)
            for (var i = 0; i < NUM_SECTORS; ++i) {
                ctx.setTransform(1, 0, 0, 1, 0, 0);
                ctx.translate(centerX, centerY);
                ctx.rotate(CHART_ORIENTATION);
                ctx.rotate(SECTOR_SIZE * i);

                var sector = sectors[i];
                if (!sector) continue;

                ctx.fillStyle = sector.color;
                ctx.beginPath();
                ctx.moveTo(0, 0);
                ctx.arc(0, 0, sector.percent * (radius / 100.0), 0, SECTOR_SIZE, false);
                ctx.lineTo(0, 0);
                ctx.fill();
                ctx.closePath();
            }

            // Draw the grid over the colored sectors
            ctx.fillStyle = '#000';
            for (var i = 0; i < NUM_SECTORS; ++i) {
                ctx.setTransform(1, 0, 0, 1, 0, 0);
                ctx.translate(centerX, centerY);
                ctx.rotate(CHART_ORIENTATION);
                ctx.rotate(SECTOR_SIZE * i);

                ctx.beginPath();
                ctx.moveTo(0, 0);
                ctx.lineTo(radius + radiusOffset, 0);
                ctx.closePath();
                ctx.stroke();
            }

            // Now, draw the sector numbers
            var textOffset = radiusOffset + 3;
            ctx.font = "10px sans-serif";
            for (var i = 0; i < NUM_SECTORS; ++i) {
                ctx.setTransform(1, 0, 0, 1, 0, 0);
                ctx.translate(centerX, centerY);
                ctx.rotate(CHART_ORIENTATION);
                ctx.rotate(SECTOR_SIZE * i + (SECTOR_SIZE / 2));
                ctx.fillText(i + 1, radius + textOffset, 0);
            }

            ctx.setTransform(1, 0, 0, 1, 0, 0);
            ctx.translate(centerX, centerY);
            ctx.fillStyle = '#000';
            // Draw the radar circles at some chart percents
            var circlesPercents = [15, 50, 64, 80, 94, 100];
            for (var i = 0; i < circlesPercents.length; ++i) {
                ctx.beginPath();
                ctx.arc(0, 0, circlesPercents[i] * (radius / 100.0), 0, 2.0 * Math.PI, false);
                ctx.closePath();
                ctx.stroke();
            }
        }

// To use this script, push data elements to the 'data' array as follows:

        //0 - 0.25 - 0 level    dim red
        //0.25 - 0.5 - 1 level  red
        //0.5 - 0.7 - 2 level   dim yellow
        //0.7 - 0.85 - 3 level  yellow
        //0.85 - 0.95 - 4 level dim green
        //0.95 - 1 - 5 level    green

        var color_green = '#00EE00';
        var color_yellow = '#FFF200';//
        var color_red = '#ED1C24';

        var color_dark_red = '#8B0000';
        var color_dark_yellow = '#FF7F00';
        var color_dark_green = '#008B00';

        var color_white = '#FFFAFA';//not estimated

        var data = new Array();

        var groupValues = ${ev1FBO.mGroupValues};
        for (var i = 0; i < 10; i++) {
            var currentColor;
            if (groupValues[i] == 0) {
                currentColor = color_white;
            } else if (groupValues[i] <= 0.25) {
                currentColor = color_dark_red;
            } else if (groupValues[i] <= 0.5) {
                currentColor = color_red;
            } else if (groupValues[i] <= 0.7) {
                currentColor = color_dark_yellow;
            } else if (groupValues[i] <= 0.85) {
                currentColor = color_yellow;
            } else if (groupValues[i] <= 0.95) {
                currentColor = color_dark_green;
            } else {
                currentColor = color_green;
            }

            data.push({parameter: (i + 1), percent: getPercentFromDouble(groupValues[i]), color: currentColor});

        }


        drawChart(data);
    });//]]>

    function getPercentFromDouble(double_number) {
        if (double_number == 0.0) {
            return 0;
        }
        else if (double_number == 1.0) {
            return 100;
        }
        else {
            var percent = double_number * 100;
            return Math.ceil(percent);
        }
    }

</script>

<style type="text/css" media="all">
    body {
        margin: 10px auto;
        width: 570px;
        font: 120%/100% Arial, Helvetica, sans-serif;
    }

    #diagram {
        padding: 0;
        margin: 0;
        font-size: 11px;
        width: 450px;
        height: 450px;
        position: absolute;
        top: 45px;
        left: 10px;
        background-color: #fff;
        color: #444;
        border: solid 1px #c4c4c4;
    }

    #diagram_h3{
        position: absolute;
        top: 500px;
        left: 150px;

    }

    .blocks {
        width: 480px;
        /*border-bottom: solid 1px #c4c4c4;*/
        background: #f7f7f7;
        padding: 7px 15px;
        margin: 0;
        /*font: bold 120%/100% Arial, Helvetica, sans-serif;*/
        border: solid 1px #c4c4c4;

        cursor: pointer;

    }

    #values_block {
        position: absolute;
        bottom: -400px;
        left: 0px;
    }

    #details_block {
        position: relative;
        top: 45px;
        left: 45px;

    }


</style>

</head>
<body>
<h3 id="diagram_h3"><i><spring2:message code="self.showpage.diagram"/> </i></h3>
<div id="diagram">

    <canvas width="400" height="400" id="radar_chart">
        <spring2:message code="self.showpage.errordiagram"/>
    </canvas>
</div>
<div id="details_block" class="blocks">
    <h3>
        <spring2:message code="self.showpage.name"/>: <i>${selfAssessmentModel.selfAssessmentName}</i>
    </h3>

    <p>
        <spring2:message code="self.showpage.description"/>: ${selfAssessmentModel.description}
    </p>

    <p>
        <spring2:message code="self.showpage.auditors"/>:${selfAssessmentModel.auditors}
    </p>

    <p>
        <spring2:message code="self.showpage.creator"/>:${selfAssessmentModel.creator}
    </p>

    <div id="values_block" class="blocks">
        <h3 id="EV1_h3">
            <spring2:message code="self.showpage.EV1description"/>

        </h3>
        <p>
            EV<sub>1</sub> = ${ev1FBO.ev1Value}
        </p>

        <h3 id="EV2_h3">
            <spring2:message code="self.showpage.EV2description"/>

        </h3>
        <p>
            EV<sub>2</sub> = 0.0
        </p>
        <h3 id="EV3_h3">
            <spring2:message code="self.showpage.EV3description"/>

        </h3>

        <p>
            EV<sub>3</sub> = 0.0
        </p>
        <h3 id="R_h3">

        </h3>
        <p>
            R = 0.0
        </p>
    </div>
</div>

</body>


</html>