import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Login } from './login.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';

import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  public formData : any = FormGroup ;
  public submitted: boolean =  false;
  constructor(private route : Router, private loginService : Login){}
  //constructor(private route : Router){}
  usernames=[];
  ngOnInit(){
    this.formData = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, Validators.required)
    })
  }
  onSubmit(){
    this.submitted=true;
    let payload ={
      email: this.formData.value.email,
      password: this.formData.value.password
    }
   // console.log("form",this.formData);
    console.log("payload",payload);
    // this.loginService.logIn(payload).then((res)=>{
    //   console.log(res)
    // }).catch(err=>{console.log(err)})
     this.loginService.logIn(payload).subscribe((response : any)=>{
      console.log("res",response);
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Login Successfull',
        showConfirmButton: false,
        timer: 1500
      })
      localStorage.setItem('token', response.email);
      localStorage.setItem('email', response.email);
     localStorage.setItem('userId', response.id);
      setTimeout(() => {
        this.route.navigate(["/echannel"]);
      }, 500);
         
    },err=>{
      console.log(err);
      //Swal.fire(err.error.error);
      // Swal.fire(
      //   'The Internet?',
      //   err.response.data.message,
      //   'question'
      // )
      console.log(err.error.error);
    })
  }
  // forbiddenNames(control:FormControl):{[s:string]:any} | null{
  //   if(this.usernames.indexOf(control.value) !== -1){
  //     return {'nameIsForbidden':true};
  //   } 
  //   return null;
  // }
 
 }
