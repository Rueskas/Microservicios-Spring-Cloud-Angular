import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Curso } from '../models/curso';
import { HttpClient } from '@angular/common/http';
import { Alumno } from '../models/alumno';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CursoService extends CommonService<Curso> {
  protected endpoint: string = '/cursos';
  constructor(http: HttpClient) { 
    super(http);
  }

  public asignarAlumnos(curso: Curso, alumnos: Alumno[]): Observable<Curso> {
    return this.http.put<Curso>(`${this.url + this.endpoint}/${curso.id}/asignar-alumnos`, alumnos,
    {headers: this.headers});
  }

  public eliminarAlumno(curso: Curso, alumno: Alumno): Observable<Curso> {
    return this.http.put<Curso>(`${this.url + this.endpoint}/${curso.id}/eliminar-alumno`, alumno,
    {headers: this.headers});
  }
}
