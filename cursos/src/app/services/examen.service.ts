import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonService } from './common.service';
import { Examen } from '../models/examen';

@Injectable({
  providedIn: 'root'
})
export class ExamenService extends CommonService<Examen> {
  private _endpoint: string = '/examenes';
  constructor(http: HttpClient) { 
    super(http);
  }
}
