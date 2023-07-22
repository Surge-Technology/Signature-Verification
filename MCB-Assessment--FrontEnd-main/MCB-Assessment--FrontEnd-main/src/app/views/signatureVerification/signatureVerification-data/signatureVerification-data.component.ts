import { Component , OnInit , ViewChild} from '@angular/core'; 
import {signatureVerificationDataService} from './signatureVerification-data.service';
import { FormControl, FormGroup, Validators } from '@angular/forms'; 
import { Router,ActivatedRoute  } from '@angular/router';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
export interface PeriodicElement{
  label:any,
  count:any,
}
 
@Component({
  selector: 'app-signatureVerification-data',
  templateUrl: './signatureVerification-data.component.html',
  styleUrls: ['./signatureVerification-data.component.scss']
})
export class signatureVerificationDataComponent implements OnInit{
  constructor(public signatureVerificationDataService:signatureVerificationDataService, private snapshot: ActivatedRoute,public route:Router) { }
   
   
  public notVerified:any;
  public verifiedOk:any;
  public verifiedNotOk:any;
  ngOnInit(): void {
    this.signatureVerificationDataService.dataCountList().then((res)=>{
     console.log("res",res)  
      this.notVerified= res.data.notVerified;
      this.verifiedOk= res.data.verifiedOk;
      this.verifiedNotOk= res.data.verifiedNotOk 
    }).catch(err=>{
     console.log(err.response)
   })
   }
   
}
