import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Global } from './global';

@Injectable({
  providedIn: 'root'
})
export class ExamenService {
  private _endpoint: string = '/examenes';
  private _url: string = Global.URL;

  constructor(private _http: HttpClient) { }
}
