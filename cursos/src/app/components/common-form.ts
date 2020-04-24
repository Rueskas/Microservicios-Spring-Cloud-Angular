import { Component, OnInit } from '@angular/core';
import { AlumnoService } from 'src/app/services/alumno.service';
import { Alumno } from 'src/app/models/alumno';
import { Router, ActivatedRoute } from '@angular/router'
import Swal from 'sweetalert2';
import { CommonService } from '../services/common.service';
import { Generic } from '../models/generic';

export class CommonFormComponent<E extends Generic, S extends CommonService<E>> implements OnInit {
  titulo: string;
  entity: E;
  errors: any;
  protected nombreModel: string;
  protected rutaRedirect: string;
  constructor( 
    protected service: S,
    protected router: Router,
    protected activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      params => {
        if(params.get('id')){
          this.service.ver(+params.get('id')).subscribe(
            e => this.entity = e,
            error => {
              if(error.status === 404){
                Swal.fire(`${this.nombreModel} No Encontrado`, `El ${this.nombreModel} con ID: ${params.get('id')} no existe`, 'error');
                this.router.navigate([this.rutaRedirect]);
              }
            }
          )
        }
      }
    )
  }

  public crear(): void{
    this.service.crear(this.entity)
    .subscribe(
      entity => {
        Swal.fire(`${this.nombreModel} Creado`, `${this.nombreModel} ${this.entity.nombre} creado con éxito`, 'success');
        this.router.navigate([this.rutaRedirect]);
     },
     error => {
       if(error.status === 400) {
        this.errors = error.error; 
        console.log(this.errors);
       }
     });
  }

  public editar(): void{
    this.service.editar(this.entity)
    .subscribe(
      entity => {
        Swal.fire(`${this.nombreModel} Editado`, `${this.nombreModel} ${this.entity.nombre} editado con éxito`, 'success');
        
        this.router.navigate(['/alumnos']);
     },
     error => {
       if(error.status === 400) {
        this.errors = error.error; 
        console.log(this.errors);
       }
     });
  }

}
