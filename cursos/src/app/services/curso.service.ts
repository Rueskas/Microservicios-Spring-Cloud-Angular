import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Global } from './global';

@Injectable({
  providedIn: 'root'
})
export class CursoService {
  private _endpoint: string = '/cursos';
  private _url: string = Global.URL;
  
  constructor(private _http: HttpClient) { }
}
