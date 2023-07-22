import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
//import { environment } from '../../../environments/environment.prod';
import  {environment} from '../../../../environments/environment.prod';
import axios from "axios";
@Injectable({providedIn:'root'})

export class Login {
   // public companyData: any = [];
    constructor(public https: HttpClient){}
    
    logIn(data:any){
       // console.log("payload",data);
      // axios.post(environment.ANGULAR_APP_BACKEND_SERVICE + "login",data);
        return this.https.post(environment.ANGULAR_APP_BACKEND_SERVICE + 'login',data);
    }
}