import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { signatureVerificationDataRoutingModule } from './signatureVerification-data-routing.module';
import { signatureVerificationDataComponent } from './signatureVerification-data.component';
import {MatIconModule} from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTableModule} from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
@NgModule({
  declarations: [
    signatureVerificationDataComponent
  ],
  imports: [
    CommonModule,
    signatureVerificationDataRoutingModule,
    MatIconModule,
    MatCardModule,
    ReactiveFormsModule, MatTableModule
  ]
})
export class signatureVerificationDataModule { }
