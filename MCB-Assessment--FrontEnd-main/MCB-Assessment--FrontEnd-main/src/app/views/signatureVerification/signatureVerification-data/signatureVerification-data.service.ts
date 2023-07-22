import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import axios from "axios";
import { environment } from "../../../../environments/environment.prod";
@Injectable({providedIn:'root'})

export class signatureVerificationDataService{
  dataCountList(){
        return axios.get(environment.ANGULAR_APP_BACKEND_SERVICE + "eventSourceStatus/count");
    }
}