import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import axios from "axios";
import { environment } from "../../../../environments/environment.prod";
@Injectable({providedIn:'root'})

export class signatureVerificationService{
   eventSourcesList(){
        return axios.get(environment.ANGULAR_APP_BACKEND_SERVICE + "by-status-name/Unassigned");
    }
   requestOpen(payload:any){
    let userId = localStorage.getItem('userId');
    let evnt = {
        "eventSourceId": payload
    }
      return axios.post(environment.ANGULAR_APP_BACKEND_SERVICE + "requests/assign/user/"+userId+"/status/Assign",evnt);
    //return axios.post(environment.ANGULAR_APP_BACKEND_SERVICE + "requests/assign/user/"+userId+"/status/5",payload);
   }
   assignedList(){
    //localhost:8090/
    alert('assignedList');
    let userId = localStorage.getItem('userId');
    alert(userId);
    return axios.get(environment.ANGULAR_APP_BACKEND_SERVICE + "user/"+userId+"/status/Assign");
   } 
}