import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
// import { AllContactsRoutingModule } from './all-contacts-routing.module';
// import { AllContactsComponent } from './all-contacts.component';
import {signatureVerificationComponent } from './signatureVerification.component';
import {SignatureVerificationRoutingModule} from './signatureVerification-routing.module';
import {
  AvatarModule,
  ButtonGroupModule,
  ButtonModule, AlertModule,
  BadgeModule,ModalModule,
  PopoverModule,
  CardModule,
  FormModule,
  GridModule,
  NavModule,
  ProgressModule,
  TableModule,
  TabsModule, SharedModule,
  ToastModule,
  TooltipModule,
  UtilitiesModule,
 } from '@coreui/angular';
import { IconModule } from '@coreui/icons-angular';
import { ChartjsModule } from '@coreui/angular-chartjs';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatSortModule } from '@angular/material/sort';
import { MatInputModule } from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatTableDataSource} from '@angular/material/table';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {SelectionModel} from '@angular/cdk/collections';
@NgModule({
  declarations: [
    signatureVerificationComponent
  ],
  imports: [
    CommonModule,
    SignatureVerificationRoutingModule,
   // AllContactsRoutingModule,
    // IconModule,
    // ChartjsModule,
    MatCheckboxModule,
    MatTableModule,MatPaginatorModule,MatCardModule,MatSortModule,MatInputModule,MatIconModule,
    AvatarModule,
  ButtonGroupModule,
  ButtonModule, AlertModule,
  BadgeModule,ModalModule,
  PopoverModule,
  CardModule,
  FormModule,
  GridModule,
  NavModule,
  ProgressModule,
  TableModule,
  TabsModule, SharedModule,
  ToastModule,
  TooltipModule,
  UtilitiesModule,
  ReactiveFormsModule 
  ]
})
export class signatureVerificationModule { }
