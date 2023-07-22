import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { signatureVerificationFormComponent } from './signatureVerification-form.component';

const routes: Routes = [{ path: '', component: signatureVerificationFormComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class signatureVerificationFormRoutingModule { }
