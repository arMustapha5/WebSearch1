/* src/main/resources/static/js/main.js */
let chart;
let currentData = [];

function generateData() {
    const size = parseInt(document.getElementById('dataSize').value);
    const range = parseInt(document.getElementById('dataRange').value);

    currentData = Array.from({length: size}, () =>
        Math.floor(Math.random() * range)
    );

    updateChart(currentData);
    document.getElementById('originalData').innerHTML =
        `<strong>Original Data:</strong> [${currentData.join(', ')}]`;
    document.getElementById('sortedData').innerHTML = '';
    document.getElementById('executionTime').innerHTML = '';
}

function updateChart(data) {
    if (chart) {
        chart.destroy();
    }

    const ctx = document.getElementById('chart').getContext('2d');
    chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: Array.from({length: data.length}, (_, i) => i + 1),
            datasets: [{
                label: 'Data Values',
                data: data,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            animation: {
                duration: 1000
            }
        }
    });
}

function sortData() {
    const algorithm = document.getElementById('algorithm').value;

    // Show loading state
    document.getElementById('executionTime').innerHTML = 'Sorting...';

    fetch('/api/sort', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            data: currentData,
            algorithm: algorithm
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            updateChart(data?.sortedData);
            document.getElementById('sortedData').innerHTML = `<strong>Sorted Data:</strong> [${data?.sortedData.join(', ')}]`;
            document.getElementById('executionTime').innerHTML = `Execution Time: ${data?.executionTimeMs}ms`;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('executionTime').innerHTML = `Error: ${error.message}. Please check the console for details.`;
        });
}

// Generate initial data when page loads
document.addEventListener('DOMContentLoaded', generateData);