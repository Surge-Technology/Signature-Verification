import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { signatureVerificationComponent } from './signatureVerification.component';

const routes: Routes = [{ path: '', component: signatureVerificationComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SignatureVerificationRoutingModule { }
