// import { Component, Input } from '@angular/core';
// import { FormControl, FormGroup } from '@angular/forms';

// import { ClassToggleService, HeaderComponent } from '@coreui/angular';

// @Component({
//   selector: 'app-default-header',
//   templateUrl: './default-header.component.html',
// })
// export class DefaultHeaderComponent extends HeaderComponent {

//   @Input() sidebarId: string = "sidebar";

//   public newMessages = new Array(4)
//   public newTasks = new Array(5)
//   public newNotifications = new Array(5)

//   constructor(private classToggler: ClassToggleService) {
//     super();
//   }
// }


import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from "../../../../environments/environment.prod";
import { ClassToggleService, HeaderComponent } from '@coreui/angular';
import axios from 'axios';

@Component({
  selector: 'app-default-header',
  templateUrl: './default-header.component.html',
})
export class DefaultHeaderComponent extends HeaderComponent {

  @Input() sidebarId: string = "sidebar";
  public userName = localStorage.getItem("username");
  public newMessages = new Array(4)
  public newTasks = new Array(5)
  public newNotifications = new Array(5)
  public profileData:any;

  constructor(private classToggler: ClassToggleService,private route : Router) {
    super();
  }
  ngOnInit(): void {
      
    if(localStorage.getItem("token")){
       console.log("LOG");
    }
    else{
      localStorage.clear();
      this.route.navigate(["/login"]);
    }
   }
  onLogOut(){
    console.log("logout")
    localStorage.clear();
   this.route.navigate(["/login"]);
  }
}
