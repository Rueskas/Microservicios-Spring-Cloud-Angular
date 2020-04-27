import { Component, OnInit } from '@angular/core';
import { CommonFormComponent } from '../common-form';
import { Examen } from 'src/app/models/examen';
import { ExamenService } from 'src/app/services/examen.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Asignatura } from 'src/app/models/asignatura';
import Swal from 'sweetalert2';
import { Pregunta } from 'src/app/models/pregunta';

@Component({
  selector: 'app-examenes-form',
  templateUrl: './examenes-form.component.html',
  styleUrls: ['./examenes-form.component.css']
})
export class ExamenesFormComponent extends CommonFormComponent<Examen, ExamenService> implements OnInit {
  entity: Examen = new Examen();
  nombreModel: string = "Examen";
  rutaRedirect: string = "/examenes";

  asignaturasPadre: Asignatura[] = [];
  asignaturasHija: Asignatura[] = [];
  errorPreguntas: boolean = false;
  constructor(service: ExamenService, router: Router, activatedRoute: ActivatedRoute) {
    super(service, router, activatedRoute);
  }

  
  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      params => {
        if(params.get('id')){
          this.service.ver(+params.get('id')).subscribe(
            e => {
              this.entity = e;
              this.cargarHijas();
            },
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
    this.service.getAsignaturas().subscribe(
      asignaturas => this.asignaturasPadre = asignaturas.filter(a => !a.padre)
    )
  }

  public cargarHijas(){
    this.asignaturasHija = this.entity.asignaturaPadre.hijos;
  }

  public compararAsignaturas(a1: Asignatura, a2: Asignatura) : boolean {
    if(a1 === undefined && a2 == undefined){
      return true;
    }

    return (a1 === null ||a2 == null || a1 == undefined ||a2 == undefined)? 
      false : 
      (a1.id == a2.id)? 
        true : false;
  }

  public agregarPregunta() : void {
    this.entity.preguntas.push(new Pregunta());
  }

  public eliminarPregunta(index: number) : void {
    this.entity.preguntas.splice(index, 1);
  }

  public crear(): void {
    this.eliminarPreguntasVacias();
    if(this.entity.preguntas.length == 0){
      //Swal.fire('Error', 'El examén debe tener preguntas.', 'error');
      this.errorPreguntas = true;
      return;
    }
    super.crear();
  }

  public editar(): void {
    this.eliminarPreguntasVacias();
    if(this.entity.preguntas.length == 0){
      //Swal.fire('Error', 'El examén debe tener preguntas.', 'error');
      this.errorPreguntas = true;
      return;
    }
    super.editar();
  }

  public eliminarPreguntasVacias(): void {
    this.entity.preguntas = 
      this.entity.preguntas.filter(p => p.texto && p.texto.trim().length > 0);
  }
}
