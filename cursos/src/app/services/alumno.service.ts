import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Global } from './global';
import { Alumno } from '../models/alumno';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService {
  private _endpoint: string = '/alumnos';
  private _url: string = Global.URL;
  private _headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private _http: HttpClient) { }

  public listar() : Observable<Alumno[]> {
    return this._http.get<Alumno[]>(this._url+this._endpoint);
  }

  public listarPaginas(page: string, size: string) : Observable<any> {
    const params = new HttpParams()
      .set('page',page)
      .set('size', size);
    return this._http.get<any>(`${this._url+this._endpoint}/page`, {params: params});
  }

  public ver(id: number) : Observable<Alumno> {
    return this._http.get<Alumno>(`${this._url+this._endpoint}/${id}`);
  }

  public crear(alumno: Alumno) : Observable<Alumno> {
    return this._http.post<Alumno>(`${this._url+this._endpoint}`, alumno, {headers: this._headers});
  }

  public editar(alumno: Alumno) : Observable<Alumno> {
    return this._http.put<Alumno>(`${this._url+this._endpoint}/${alumno.id}`, alumno, {headers: this._headers});
  }
  
  public eliminar(id: number) : Observable<void> {
    return this._http.delete<void>(`${this._url+this._endpoint}/${id}`);
  }
}
