import { Component } from '@angular/core';
import { count } from 'rxjs';
import { OrdersService } from 'src/app/services/orders/orders.service';
import { ProductService } from 'src/app/services/product/product-service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {


  totalUsers!: number;
  totalProducts! : number;
  totalOrders! : number;
  totalRevenue! : number;
 
  constructor(private userService : UserService, private productService : ProductService, private orderService : OrdersService) {}

  dataOrders: any;
  optionsOrders: any;

  dataClients : any;
  optionsClients : any;

  ngOnInit(): void {
    this.getCountOrders();
    this.getCountProducts();
    this.getCountUsers();
    this.getCountOrdersByMonth();
    this.setupOrdersChart();
    this.setupClientsChart();
    this.getCountClientByMonth();
    this.getTotalRevenue();
  }
    
  getTotalRevenue()
  {
    this.orderService.getTotalRevenue().subscribe(
      revenue=>{
        this.totalRevenue = revenue;
        console.log(this.totalRevenue)

      },
      error=>
      
      {
        console.log("ERROR GETTING COUNT OF ORDERS", error)
      }
    )
  }

  getCountOrders()
  {
    this.orderService.getTotalOrders().subscribe(
      count1 =>
      {
        this.totalOrders = count1;
        console.log(this.totalOrders)
      },
      error =>
      {
        console.log("ERROR GETTING COUNT OF ORDERS", error)
      }
    )
  }

  getCountProducts()
  {
    this.productService.getTotalProducts().subscribe(
      count =>
      {
        this.totalProducts = count;
      },
      error =>
      {
        console.log('Error retrieving total products:', error)
      }      
    )
  }



  getCountUsers() {
    this.userService.getTotalUsers().subscribe(
      count => {
        this.totalUsers = count;
      },
      error => {
        console.log('Error retrieving total users:', error);
      }
    );
  }

  getCountClientByMonth(): void {
    this.userService.getTotalUsersPerMonth()
      .subscribe(
        (response: any[]) => {
          this.updateChartUsers(response);
          console.log(response)
        },
        (error: any) => {
          console.error(error);
        }
      );
  }

  setupClientsChart() {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
    const surfaceBorder = documentStyle.getPropertyValue('--surface-border');
  
    this.optionsClients = {
      maintainAspectRatio: false,
      aspectRatio: 0.6,
      plugins: {
        legend: {
          labels: {
            color: textColor
          }
        }
      },
      scales: {
        x: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false
          }
        },
        y: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false
          }
        }
      }
    };
  }
  
  
  updateChartUsers(data: any[]): void {
    const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    const userCounts = Array(labels.length).fill(0);
    const documentStyle = getComputedStyle(document.documentElement);
  
    for (const item of data) {
      const monthIndex = item[0] - 1;
      const count = item[1];
      userCounts[monthIndex] = count;
    }
  
    this.dataClients = {
      labels: labels,
      datasets: [
        {
          label: 'Users Created',
          data: userCounts,
          fill: false,
          borderColor: documentStyle.getPropertyValue('--blue-500'),
          tension: 0.4
        }
      ]
    };
  }
  

  getCountOrdersByMonth(): void {
    this.orderService.getCountByMonth()
      .subscribe(
        (response: any[]) => {
          this.updateChart(response);
        },
        (error: any) => {
          console.error(error);
        }
      );
  }


  setupOrdersChart()
  {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
    const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

    this.optionsOrders = {
      maintainAspectRatio: false,
      aspectRatio: 0.6,
      plugins: {
        legend: {
          labels: {
            color: textColor
          }
        }
      },
      scales: {
        x: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false
          }
        },
        y: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false
          }
        }
      }
    };
  }
  

  updateChart(data: any[]): void {
    const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    const checkedOrdersData = Array(labels.length).fill(0);
    const uncheckedOrdersData = Array(labels.length).fill(0);
    const documentStyle = getComputedStyle(document.documentElement);

    for (const item of data) {
      const month = item[0]; 
      const checked = item[1];
      const count = item[2]; 
      
      const monthIndex = month - 1;

      if (checked) {
        checkedOrdersData[monthIndex] = count;
      } else {
        uncheckedOrdersData[monthIndex] = count;
      }
    }

    this.dataOrders = {
      labels: labels,
      datasets: [
        {
          label: 'Checked Orders',
          data: checkedOrdersData,
          fill: false,
          borderColor: documentStyle.getPropertyValue('--blue-500'),
          tension: 0.4
        },
        {
          label: 'Unchecked Orders',
          data: uncheckedOrdersData,
          fill: false,
          borderColor: documentStyle.getPropertyValue('--pink-500'),
          tension: 0.4
        }
      ]
    };
  }



  
}
