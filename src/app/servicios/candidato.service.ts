import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';




@Injectable({
  providedIn: 'root',
})
export class CandidatoService {
  private baseUrl = 'http://localhost:8080/BackendPrueba-1.0-SNAPSHOT/api';

  constructor(private http: HttpClient) {}

  registrarCandidato(candidato: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, candidato);
  }

  obtenerCandidatos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }

  
  // Manejo de errores
 private handleError(error: HttpErrorResponse): Observable<never> {
  let errorMessage = 'Ocurrió un error desconocido';
  if (error.error instanceof ErrorEvent) {
    // Error del cliente o de red
    errorMessage = `Error del cliente o red: ${error.error.message}`;
  } else {
    // Error del servidor
    errorMessage = `Error del servidor ${error.status}: ${error.message}`;
  }
  console.error(errorMessage); // Mostrar el error en la consola
  return throwError(() => new Error(errorMessage)); // Lanza un error para manejarlo en el componente
}
}
 


