/*데이터*/
const scatter_data = [
  {x : 5, y:1},
  {x : 3, y:5},
  {x : 7, y:3},
  {x : 2, y:7},
  {x : 9, y:9},
  {x : 10, y:2},
  {x : 8, y:9},
  {x : 1, y:1},
  {x : 3, y:2},
];

const scatter_data2 = [
  {x : 9, y:7},
  {x : 5, y:2},
  {x : 1, y:6},
  {x : 8, y:3},
  {x : 2, y:4},
  {x : 1, y:8},
  {x : 4, y:3},
  {x : 8, y:6},
  {x : 6, y:4},
];

/* create chart*/
const ctx = document.getElementById('myChart').getContext('2d');
const myChart = new Chart(ctx, {
  type: 'scatter',
  data: {
    datasets: [
      {
        label: 'Dataset 1',
        data: scatter_data,
        backgroundColor: 'rgb(255, 99, 132)',
      },
      {
        label: 'Dataset 2',
        data: scatter_data2,
        backgroundColor: 'rgb(99, 255, 132)',
      }
    ]
  },
  options:  {
    elements: {
      point:  {
        radius: 6,
        pointStyle: 'rectRot'
      }
    }
  }
});