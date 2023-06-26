

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {BaseComponent} from "../../shared/components/base.component";
import {TestStepResult} from "../../models/test-step-result.model";
import {TestStepResultService} from "../../services/test-step-result.service";
import {TestCaseService} from "../../services/test-case.service";
import axios from 'axios';


@Component({
  selector: 'app-test-step-result-details',
  templateUrl: './test-step-result-details.component.html',
  styles: [],
  host: {'class': 'd-flex ts-col-100'}
})
export class TestStepResultDetailsComponent extends BaseComponent implements OnInit {
  public testStepResultId: Number;
  public testStepResult: TestStepResult;
  public isFetchingCompleted: boolean = false;

  constructor(
              public route: ActivatedRoute,
              public testStepResultService: TestStepResultService,
              public testCaseService: TestCaseService,
              public router: Router) {
    super();
  }

  ngOnInit() {
    if(this.router.url.includes('step_results'))//TODO need to fix route[JAYAVEL]
    this.route.params.subscribe((params: Params) => {
      this.route.parent.params.subscribe((parentParams: Params) => {
        this.testStepResultId = params.resultId;
        let newData = Object.assign({testResultId: parentParams.resultId, resultId: this.testStepResultId}, params)
        this.pushToParent(this.route, newData);
        this.fetchTestStepResult();
      })
    });
  }


 fetchTestStepResult() {
   if(this.router.url.includes('step_results'))//TODO need to fix route[JAYAVEL]
   this.testStepResultService.show(this.testStepResultId).subscribe(res => {
     this.testCaseService.show(res?.testCaseId).subscribe(testcase => {
       const fileLoginUrl = 'https://fileupload-test.machint.com/login';
       const fileLoginData = {
         "requestdata": {
             "username":"Machint",
             "userpassword": "Machint@123"
         },
         "requestid": 21835674,
         "requestsrc": "ui",
         "requesttype": "listdata"
     };
       const options = {
         method: 'POST',
         headers: {
           'Content-Type': 'application/json'
         },
         body: JSON.stringify(fileLoginData)
       };
       fetch(fileLoginUrl, options)
         .then(response => response.json())
         .then(data => {
           let jwtToken = data.response.jwt_token;
           const fileDownloadUrl = 'https://filedownload-test.machint.com/get-uploaded-file?file_name='+res?.screenshotName;
           const toBase64 = (file) =>
                   new Promise((resolve, reject) => {
                  const reader = new FileReader();
                  reader.readAsDataURL(file);
                  reader.onload = () => resolve(reader.result);
                  reader.onerror = (error) => reject(error);
            });
    axios.get(fileDownloadUrl,{headers: {
             'Authorization':"Bearer "+jwtToken,
           },
           responseType:'blob'},)
 .then(async (response) => {
   const blob=new Blob([response.data],{type:response.data.type})
   let convertedData=await toBase64(blob);
   console.log("convertedData",convertedData)
   this.testStepResult.screenFile =convertedData;
 })
 .catch(error => console.error(error));
         })
         .catch(error => console.error(error));
       if (res.isForLoop || res.isConditionalElse) {
         this.testStepResultService.findAll("parentResultId:" + res.id).subscribe(steps => {
           if (steps.content.length > 0)
             this.navigate(res.testCaseResultId, steps.content.find(st => st.isFailed || st.isAborted || st.isNotExecuted) || steps.content[0]);
           this.testStepResult = res;
         });
       } else if (res.isStepGroup) {
         this.testStepResultService.findAll("groupResultId:" + res.id).subscribe(steps => {
           if (steps.content.length > 0)
             this.navigate(res.testCaseResultId, steps.content.find(st => st.isFailed || st.isAborted || st.isNotExecuted) || steps.content[0]);
           this.testStepResult = res;
         })
       } else {
         this.testStepResult = res;
       }
       this.isFetchingCompleted = true;
     })
    
   });
 }

   navigate(testCaseResultId: Number, testStepResult: TestStepResult) {
    this.router.navigate(['/td/test_case_results', testCaseResultId, 'step_results', testStepResult.id]);
  }

}
