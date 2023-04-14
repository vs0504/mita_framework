import { HttpClient } from '@angular/common/http';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,FormControl } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TestPlanResult } from 'app/models/test-plan-result.model';
import { HttpHeadersService } from 'app/shared/services/http-headers.service';
import { UrlConstantsService } from 'app/shared/services/url.constants.service';
import { COMMA, ENTER } from '@angular/cdk/keycodes';

import {  MatChipInputEvent } from '@angular/material/chips';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
})
export class EmailComponent  implements OnInit {
    @Input('formGroup') emailPlanForm : FormGroup;
    @Inject(MAT_DIALOG_DATA) public testPlanResult: { data: any }
    //@Input() testPlanResult: TestPlanResult;
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
            time:['']
        });
    }
  emailHandler(){
    console.log("this----->",this)
  
   
    let requestData={
      emailList:this.emailPlanForm.value.email,
      testPlanId:this.testData.testPlanResult.testPlan.id,
      runId:this.testData.testPlanResult.id,
      plannedDate:this.emailPlanForm.value.date,
      plannedTime: this.emailPlanForm.value.time

  };

  this.http.post(this.URLConstants.sendTestPlanResultURL, requestData, {headers: this.httpHeaders.contentTypeApplication}).subscribe(data => {
    console.log("data--->",data)
}, error => {
  console.log("error-->",error);
});
//alert("wait for some time");
  // console.log("requestData--->",requestData);
  //   axios.post(this.URLConstants.sendTestPlanResultURL, requestData,{headers: this.httpHeaders.contentTypeApplication})
  //   .then(response => {
  //     console.log("response--->",response)
  //   })
  //   .catch(error => {
        
  //       console.error('There was an error!', error);
  //   });
    this.emailDialogRef.close();
  }

}