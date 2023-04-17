import { HttpClient } from '@angular/common/http';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TestPlanResult } from 'app/models/test-plan-result.model';
import { HttpHeadersService } from 'app/shared/services/http-headers.service';
import { UrlConstantsService } from 'app/shared/services/url.constants.service';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
})
export class EmailComponent  implements OnInit {
    @Input('formGroup') emailPlanForm : FormGroup;
    @Inject(MAT_DIALOG_DATA) public testPlanResult: { data: any };
    public today:any;
    public emailValidationMessage: any;
    constructor(
      @Inject(MAT_DIALOG_DATA) public testData: {testPlanResult:TestPlanResult},
      private formBuilder: FormBuilder, 
      private http: HttpClient,
      private httpHeaders: HttpHeadersService,
      private URLConstants: UrlConstantsService,
      public emailDialogRef: MatDialogRef<EmailComponent>) { }
    ngOnInit() {
        this.emailPlanForm = this.formBuilder.group({
            email: [''],
            date:[''],
            time:[''],

        });
        this.today = new Date().toLocaleDateString('sv');
    }
  emailHandler(){
    const expression: RegExp = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;
    const result: boolean = expression.test(this.emailPlanForm.value.email);
    if(result){
      this.emailValidationMessage = "";
      let requestData={
        emailList:this.emailPlanForm.value.email,
        testPlanId:this.testData.testPlanResult.testPlan.id,
        runId:this.testData.testPlanResult.id,
        plannedTime: this.emailPlanForm.value.date+" "+this.emailPlanForm.value.time+":00"

      };
    this.http.post(this.URLConstants.sendTestPlanResultURL, requestData, {headers: this.httpHeaders.contentTypeApplication}).subscribe(data => {
    console.log("data--->",data)
}, error => {
  console.log("error-->",error);
});
    this.emailDialogRef.close();
}
else{
  this.emailValidationMessage = "Invalid email";
}
  }

}