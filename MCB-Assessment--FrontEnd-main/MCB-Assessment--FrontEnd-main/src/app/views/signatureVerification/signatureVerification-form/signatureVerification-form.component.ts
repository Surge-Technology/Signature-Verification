import { Component , OnInit , ViewChild} from '@angular/core'; 
import {signatureVerificationFormService} from './signatureVerification-form.service';
import { FormControl, FormGroup, Validators } from '@angular/forms'; 
import { Router,ActivatedRoute  } from '@angular/router';
//import {Echannel_Verification} from "" 
//import {MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogModule} from '@angular/material/dialog';

import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-signatureVerification-form',
  templateUrl: './signatureVerification-form.component.html',
  styleUrls: ['./signatureVerification-form.component.scss']
})
export class signatureVerificationFormComponent implements OnInit{
  constructor( public EchannelformService:signatureVerificationFormService, private snapshot: ActivatedRoute,public route:Router) { }
  public userData : any = FormGroup; 
  public id : string | null = this.snapshot.snapshot.paramMap.get('id');
  public assignedId : string | null = this.snapshot.snapshot.paramMap.get('assigned');
  public roles : any = ["SuperUser","Admin","CEO"]
  public submitted = false;
  
  public reasonOptions:any;
  public UpdateData :any;
  public outComeId :any;
  public disableSelctReasn:any;
  src = 'https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf';


  // openDialog(): void {
  //   const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
  //     data: {name: "this.name", animal:" this.animal"},
  //   });
  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log(`Dialog result: ${result}`);
  //   });
  // }




  ngOnInit(): void {
    let arr =[{id:1,name:"Signature Missing"},{id:2,name:"Signature differs from T24"},{id:3,name:"Signature not recorded on T24"},{id:4,name:"Signature wrongly scanned"},{id:5,name:"Signature and name mismatched"},{id:6,name:"Signature illegible"},{id:7,name:"One authorised Signature missing"},{id:8,name:"Signature not authorised to sign"},]
    
    this.reasonOptions = arr;
    
    this.userData = new FormGroup({
      'reference': new FormControl(null, Validators.required),
        'currency'   : new FormControl(null, Validators.required),
       'amount'   : new FormControl(null, Validators.required),
       'accountNo' : new FormControl(null, Validators.required),
       'debitCurrency': new FormControl(null, Validators.required),
        'accountShortName': new FormControl(null, Validators.required),
         'amountInMur': new FormControl(null, Validators.required),
         'comments': new FormControl(null, Validators.required),
        'paymentDetails1': new FormControl(null, Validators.required),
        'paymentDetails2': new FormControl(null, Validators.required),
        'paymentDetails3': new FormControl(null, Validators.required), 
        'paymentDetails4': new FormControl(null, Validators.required),
        //    'signyesORno': new FormControl(null, Validators.required),
        //  'reasonSelect': new FormControl(null, Validators.required),
      //   'customerCalledOn': new FormControl(null, Validators.required),
      //   'comment': new FormControl(null, Validators.required),
      //  'debitCurrency': new FormControl(null, Validators.required),


     })



      
    this.EchannelformService.eventSourcesGetById(this.id).then((res)=>{
     console.log('reee',res.data)
        this.userData.setValue ({
          'reference': res.data.sourceBu,
        'currency' :res.data.transactionCurrency,
        'amount':res.data.transactionAmount,
         'accountNo':res.data.debitAccountNumber,
         'debitCurrency':res.data.transactionCurrency,
          'accountShortName':res.data.accountShortName,
           'paymentDetails1':res.data.paymentDetails1,
            'comments':res.data.comments,
           'paymentDetails2':res.data.paymentDetails2,
           'paymentDetails3':res.data.paymentDetails3,
           'paymentDetails4':res.data.paymentDetails4,
        'amountInMur':res.data.amountInMur,
      //     'outcomeSelect':{id:res.data.outCome.id,name:res.data.outCome.name},
          
      //     'contactPerson':res.data.contactPerson,
      //     'customerCalledOn':res.data.customerCalledOn,
      //     'comment':res.data.comment,
       })
      this.outComeId=res.data.status.name
      this.UpdateData={
        "businessKey": res.data.businessKey,
        "priority": res.data.priority,
        "application": res.data.application,
        "documentCaptureReference": res.data.documentCaptureReference,
        "amountInMur": res.data.amountInMur,
        "sourceBu": res.data.sourceBu,
        "accountShortName": res.data.accountShortName,
        "debitAccountCurrency": res.data.debitAccountCurrency,
        "paymentDetails1": res.data.paymentDetails1,
        "paymentDetails2": res.data.paymentDetails2,
        "paymentDetails3": res.data.paymentDetails3,
        "paymentDetails4": res.data.paymentDetails4,
        "verified": "verified",
        // "dcpReference":res.data.dcpReference,
        // "accountName": res.data.accountName,
         "transactionCurrency": res.data.transactionCurrency,
        "transactionAmount":res.data.transactionAmount,
        // "lockedBy": res.data.lockedBy,
         "debitAccountNumber": res.data.debitAccountNumber,
         "discrepancyReason": res.data.discrepancyReason,
        // "accountCurrency": res.data.accountCurrency,
        // "beneficiaryName": res.data.beneficiaryName,
        // "custInfoMkr": res.data.custInfoMkr,
        // "accountInfoMkr": res.data.accountInfoMkr,
        // "extension":res.data.extension,
        // "contactPerson": res.data.contactPerson,
        // "customerCalledOn": res.data.customerCalledOn,
         "comments":res.data.comments,
    }




 

    }).catch(err=>{
     console.log(err.response)
   })
   }
   changeGender(e:any) {
    this.disableSelctReasn=e.target.value
    if(e.target.value==="yes"){
      console.log("if")
      this.disableSelctReasn="disable"
    }else{
      console.log("else")
      this.disableSelctReasn="enable"
    }
   // e.target.value==true?this.disableSelctReasn="disable":this.disableSelctReasn="enable";
    console.log( this.disableSelctReasn,typeof(e.target.value),e.target.value);
  
  }
   onSubmit(){
    this.submitted = true;
    console.log("this.userData.value",this.userData.value)
   }
   proceedHandle=()=>{
    console.log("p",this.UpdateData);
    this.EchannelformService.proceedUpdate(this.outComeId,this.id,this.UpdateData).then((res)=>{
      console.log(res);
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Proceed Success',
        showConfirmButton: false,
        timer: 1500
      });
       this.route.navigate(["signatureVerification"])
    }).catch(err=>{console.log(err)})

   }
   rejectHandle=()=>{
     
    this.EchannelformService.rejectUpdate(this.outComeId,this.id,this.UpdateData).then((res)=>{
      console.log(res);
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Reject Success',
        showConfirmButton: false,
        timer: 1500
      });
       this.route.navigate(["signatureVerification"])
    }).catch(err=>{console.log(err)})
   }
}





// @Component({
//   selector: 'dialog-overview-example-dialog',
//   templateUrl: 'dialog-overview-example-dialog.html',
//   standalone: true,
//   imports: [MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule],
// })
// export class DialogOverviewExampleDialog {
//   constructor(
//     public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
  
//   ) {}

//   onNoClick(): void {
//     this.dialogRef.close();
//   }
// }