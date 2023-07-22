import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { signatureVerificationDataComponent } from './signatureVerification-data.component';

const routes: Routes = [{ path: '', component: signatureVerificationDataComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class signatureVerificationDataRoutingModule { }
