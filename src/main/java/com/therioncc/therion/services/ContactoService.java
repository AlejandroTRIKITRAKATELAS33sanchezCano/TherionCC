package com.therioncc.therion.services;

        import com.therioncc.therion.models.Contacto;
        import com.therioncc.therion.repositories.IContactoRepositorio;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

@Service
public class ContactoService {

    @Autowired
    private final IContactoRepositorio contactoRepositorio;

    public ContactoService(IContactoRepositorio contactoRepositorio) {
        this.contactoRepositorio = contactoRepositorio;
    }

    public void registrarContacto(Contacto contacto) {
        // Aquí puedes realizar validaciones, transformaciones, etc.

        contactoRepositorio.save(contacto);

    }
}
