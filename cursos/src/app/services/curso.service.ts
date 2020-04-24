import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Curso } from '../models/curso';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CursoService extends CommonService<Curso> {
  protected endpoint: string = '/cursos';
  constructor(http: HttpClient) { 
    super(http);
  }
}
