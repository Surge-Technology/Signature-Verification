import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { signatureVerificationFormRoutingModule } from './signatureVerification-form-routing.module';
import { signatureVerificationFormComponent } from './signatureVerification-form.component';
import {MatIconModule} from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { NgSelectModule } from '@ng-select/ng-select';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogModule} from '@angular/material/dialog';
@NgModule({
  declarations: [
    signatureVerificationFormComponent
  ],
  imports: [
    CommonModule,
    signatureVerificationFormRoutingModule,
    MatIconModule,
    MatCardModule,
    ReactiveFormsModule,
    NgSelectModule,
    PdfViewerModule
  ] 
})
export class SignatureVerificationFormModule { }
