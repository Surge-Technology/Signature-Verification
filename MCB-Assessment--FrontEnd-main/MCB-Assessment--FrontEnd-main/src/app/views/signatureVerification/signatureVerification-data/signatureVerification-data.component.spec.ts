import { ComponentFixture, TestBed } from '@angular/core/testing';

import { signatureVerificationDataComponent } from './signatureVerification-data.component';

describe('EchannelDataComponent', () => {
  let component: signatureVerificationDataComponent;
  let fixture: ComponentFixture<signatureVerificationDataComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [signatureVerificationDataComponent]
    });
    fixture = TestBed.createComponent(signatureVerificationDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
