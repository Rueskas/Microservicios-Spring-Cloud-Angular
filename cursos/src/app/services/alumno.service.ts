import { Injectable } from '@angular/core';
import { Alumno } from '../models/alumno';
import { CommonService } from './common.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService extends CommonService<Alumno> {
  protected endpoint: string = '/alumnos';
  constructor(http: HttpClient) { 
    super(http);
  }
}
