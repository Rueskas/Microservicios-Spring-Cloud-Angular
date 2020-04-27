import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonService } from './common.service';
import { Examen } from '../models/examen';
import { Asignatura } from '../models/asignatura';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExamenService extends CommonService<Examen> {
  protected endpoint: string = '/examenes';
  constructor(http: HttpClient) { 
    super(http);
  }

  public getAsignaturas() : Observable<Asignatura[]> {
    return this.http.get<Asignatura[]>(`${this.url+this.endpoint}/asignaturas`);
  }
}
