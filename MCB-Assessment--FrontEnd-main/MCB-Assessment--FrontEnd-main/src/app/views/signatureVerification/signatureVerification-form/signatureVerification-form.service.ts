import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import axios from "axios";
import { environment } from "../../../../environments/environment.prod";
@Injectable({providedIn:'root'})

export class signatureVerificationFormService{
    eventSourcesGetById(Id:any){
        console.log("idddd",Id)
        return axios.get(environment.ANGULAR_APP_BACKEND_SERVICE + "eventSource/"+Id);
    }
   outcomeslist( ){
          return axios.get(environment.ANGULAR_APP_BACKEND_SERVICE + "OutComes" );
   }
   proceedUpdate(statusNme:any,eventId:any,payload:any){
    let userId = localStorage.getItem('userId');
    //user/{userId}/eventSource/{eventSourceId}/status/{statusName}
    return axios.put(environment.ANGULAR_APP_BACKEND_SERVICE + "user/"+userId+"/eventSource/"+eventId+"/status/Approve",payload);
}
rejectUpdate(outcomeId:any,eventId:any,payload:any){
    let userId = localStorage.getItem('userId');
    //return axios.put(environment.ANGULAR_APP_BACKEND_SERVICE + "outCome/"+outcomeId+"/user/"+userId+"/eventSource/"+eventId+"/status/Reject",payload);
    return axios.put(environment.ANGULAR_APP_BACKEND_SERVICE + "user/"+userId+"/eventSource/"+eventId+"/status/Reject",payload);
}
}