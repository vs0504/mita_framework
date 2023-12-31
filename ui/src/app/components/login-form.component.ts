import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SessionService} from "../shared/services/session.service";
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationConfigService} from "../services/authentication-config.service";
import {AuthenticationConfig} from "../models/authentication-config.model";
import {AuthenticationType} from "../shared/enums/authentication-type.enum";
import {MatSnackBar} from '@angular/material/snack-bar';
import { PermissionService } from 'app/shared/services/permissions.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styles: []
})
export class LoginFormComponent implements OnInit {
  public inTransit: boolean;
  public token: string;
  public loginForm: FormGroup;
  public authConfig: AuthenticationConfig;
  public isGoogleAuth: boolean;
  public error: String;
  @ViewChild('loginFormElm') loginFormElm: ElementRef<HTMLFormElement>;

  constructor(
    private _snackBar: MatSnackBar,
    private router: Router,
    private authConfigService: AuthenticationConfigService,
    private sessionService: SessionService,
    private permissionService:PermissionService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    })
    this.fetchAuthConfig();
  }

  get badCredentials(): boolean {
    return this.route.snapshot.queryParams.error == 'bad_cred';
  }

  get invalidAuthentication(): boolean{
    return this.route.snapshot.queryParams.error == 'bad_auth';
  }

  get notActivated(): boolean{
    return this.route.snapshot.queryParams.error == 'not_activated';
  }


  login() {
    //this.loginFormElm.nativeElement.submit();
    this.sessionService.login(
      this.loginForm.getRawValue()['username'],
      this.loginForm.getRawValue()['password'],
    ).subscribe(res => {
      console.log("login response---->",res);
      this.inTransit = false;
      this.permissionService.retrievePermissions().subscribe(res => {
        sessionStorage.setItem("permissions", JSON.stringify(res));
      }, err => {
         this.error = err;
      console.log("error while permissionService--->",this.error)
      })
      this.router.navigate(['dashboard']);
    }, err => {
     
      
      this.inTransit = false;
      this.error = err.error.error;
      console.log("error while login--->",this.error)
      if(this.error!=null){
      if(this.error.includes("user name and password is disabled") || this.error.includes("Problem in configured authentication provider")){
        this.router.navigate(['login'],{queryParams: {error:'bad_auth'}});
      } else if(this.error.includes("not activated")){
        this.router.navigate(['login'],{queryParams: {error:'not_activated'}})
      } else {
        this.router.navigate(['login'],{queryParams: {error:'bad_cred'}});
      } }
      else {
        this._snackBar.open("Invalid credentials","",{"duration":2000});
      }
    
    })
  }

  signupRedirect(){
    this.router.navigate(['onboarding']);
  }

  fetchAuthConfig()
  {
    this.authConfigService.find().subscribe(
      (authConfig) => {
        this.authConfig = authConfig;
        this.isGoogleAuth = authConfig.authenticationType == AuthenticationType.GOOGLE;
      });
  }
}
