import { Injectable } from '@angular/core';
import { Alumno } from '../models/alumno';
import { CommonService } from './common.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService extends CommonService<Alumno> {
  protected endpoint: string = '/alumnos';
  constructor(http: HttpClient) { 
    super(http);
  }

  public crearConFoto(alumno: Alumno, file: File) : Observable<Alumno>{
    const formData = new FormData();
    formData.append('file', file);
    formData.append('nombre', alumno.nombre);
    formData.append('apellidos', alumno.apellidos);
    formData.append('email', alumno.email);
    return this.http.post<Alumno>(`${this.url}${this.endpoint}/crear-con-foto`, formData);
  }

  public editarConFoto(alumno: Alumno, file: File) : Observable<Alumno>{
    const formData = new FormData();
    formData.append('file', file);
    formData.append('nombre', alumno.nombre);
    formData.append('apellidos', alumno.apellidos);
    formData.append('email', alumno.email);
    return this.http.put<Alumno>(`${this.url}${this.endpoint}/editar-con-foto/${alumno.id}`, formData);
  }

  public filtrar(term: string) : Observable<Alumno[]> {
    return this.http.get<Alumno[]>(`${this.url + this.endpoint}/filtrar/${term}`);
  }
}
