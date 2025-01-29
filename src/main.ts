import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Importa FormsModule

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    importProvidersFrom(FormsModule), // Incluye FormsModule aquí
  ],
}).catch((err) => console.error(err));
