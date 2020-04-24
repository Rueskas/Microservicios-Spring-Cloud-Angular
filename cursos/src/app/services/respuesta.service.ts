import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Global } from './global';

@Injectable({
  providedIn: 'root'
})
export class RespuestaService {
  private _endpoint: string = '/respuestas';
  private _url: string = Global.URL;
  constructor(private _http: HttpClient) { }
}
