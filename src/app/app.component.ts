import { Component } from '@angular/core';
import { CandidatoService } from './servicios/candidato.service';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Importa FormsModule
import { CommonModule } from '@angular/common'; // Para *ngFor y otras directivas comunes

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Frontend';
  candidato = {
    nombrePresidente: '',
    cedula: '',
    cedulaPresidente: '',
    nombreVicepresidente: '',
    nombreAsambleista1: '',
    nombreAsambleista2: '',
    nombreVocal1: '',
    nombreVocal2: ''




  };

  candidatos: any[] = [];

  constructor(private candidatoService: CandidatoService) {}

  registrarCandidato() {
    this.candidatoService.registrarCandidato(this.candidato).subscribe(() => {
      
    });
  }
  

    
      
    }
  




