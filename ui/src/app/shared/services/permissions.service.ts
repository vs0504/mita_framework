import { HttpClient } from "@angular/common/http";
import { HttpHeadersService } from "./http-headers.service";
import { UrlConstantsService } from "./url.constants.service";
import { Observable, throwError } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { Injectable } from "@angular/core";


@Injectable()
export class PermissionService { 

    constructor(
    private http: HttpClient,
    private httpHeaders: HttpHeadersService,
    private URLConstants: UrlConstantsService) {
    }
    
 public retrievePermissions(): Observable<any> {
    return this.http.get<any>(this.URLConstants.retrievePermissionsUrl)
      .pipe(
        map(data => data),
        catchError(() => throwError('Problem while deleting Workspace'))
      )
  }
} 