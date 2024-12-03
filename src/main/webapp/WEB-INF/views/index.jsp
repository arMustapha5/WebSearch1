<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="<spring:url value='/resources/css/styles.css' />" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sorting Algorithm Visualizer</title>
    <link rel="stylesheet" href="<spring:url value="/css/style.css" />">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
</head>
<body>
<div class="container">
    <h1>KRYStov Sorting Algorithm Visualizer</h1>

    <div class="control-panel">
        <div class="input-group">
            <label for="algorithm">Algorithm</label>
            <select id="algorithm">
<%--                <option value="heapsort">Heap Sort</option>--%>
                <option value="quicksort">Quick Sort</option>
                <option value="mergesort">Merge Sort</option>
                <option value="radixsort">Radix Sort</option>
                <option value="bucketsort">Bucket Sort</option>
            </select>
        </div>

        <div class="input-group">
            <label for="dataSize">Data Size</label>
            <input type="number" id="dataSize" value="20" min="5" max="100">
        </div>

        <div class="input-group">
            <label for="dataRange">Data Range</label>
            <input type="number" id="dataRange" value="100" min="10" max="1000">
        </div>

        <div class="input-group">
            <label>&nbsp;</label>
            <button onclick="generateData()">Generate Data</button>
        </div>

        <div class="input-group">
            <label>&nbsp;</label>
            <button onclick="sortData()">Sort Data</button>
        </div>
    </div>

    <div class="visualization">
        <canvas id="chart"></canvas>
    </div>

    <div class="results">
        <h3>Results</h3>
        <div id="originalData"></div>
        <div id="sortedData"></div>
        <div id="executionTime" class="execution-time"></div>
    </div>
</div>

<script src="<spring:url value="/js/main.js" />"></script>
</body>
</html>

