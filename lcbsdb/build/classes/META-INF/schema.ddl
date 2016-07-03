
    create table ConfiguracionEmpresa (
        id varchar(255) not null,
        aceptaCuponera boolean not null,
        activo boolean not null,
        claveLdap varchar(255),
        nombre varchar(255),
        pagoOnlineCoche boolean not null,
        reservaPasajes boolean not null,
        trasferirPasajes boolean not null,
        urlAcceso varchar(255),
        urlLdap varchar(255),
        usuarioLdap varchar(255),
        validesReservasHoras int4,
        primary key (id)
    );

    create table ConfiguracionEmpresa_emails (
        ConfiguracionEmpresa_id varchar(255) not null,
        email varchar(255)
    );

    create table ConfiguracionEmpresa_telefonos (
        ConfiguracionEmpresa_id varchar(255) not null,
        telefono varchar(255)
    );

    create table Cuponera (
        id varchar(255) not null,
        saldo float4 not null,
        primary key (id)
    );

    create table DiasGruposHorarios (
        GrupoHorarioId varchar(255) not null,
        DiasEspecificos timestamp
    );

    create table Encomienda (
        id varchar(255) not null,
        ciEmisor varchar(255),
        ciReceptor varchar(255),
        direccionReceptor varchar(255),
        fechaEntrega timestamp,
        fechaIngreso timestamp,
        monto float4 not null,
        pagaReceptor boolean not null,
        retiraEnSucursal boolean not null,
        telefono varchar(255),
        destino_id varchar(255),
        emisor_id varchar(255),
        estadoActual_id varchar(255),
        origen_id varchar(255),
        receptor_id varchar(255),
        reglaCobro_id varchar(255),
        viajeAsignado_id varchar(255),
        primary key (id)
    );

    create table Encomienda_HistorialEstadosEncomienda (
        Encomienda_id varchar(255) not null,
        estados_id varchar(255) not null
    );

    create table EstadosEncomienda (
        id varchar(255) not null,
        nombre varchar(255),
        primary key (id)
    );

    create table GrupoHorario (
        id varchar(255) not null,
        nombre varchar(255),
        primary key (id)
    );

    create table GrupoHorario_Horario (
        GrupoHorario_id varchar(255) not null,
        horarios_id varchar(255) not null
    );

    create table HistorialEstadosEncomienda (
        id varchar(255) not null,
        fecha timestamp,
        estado_id varchar(255),
        primary key (id)
    );

    create table Horario (
        id varchar(255) not null,
        nombre varchar(255),
        primary key (id)
    );

    create table MantenimientoVehiculo (
        id varchar(255) not null,
        costo float4 not null,
        descripcionCompleta varchar(255),
        descripcionReducida varchar(255),
        fechaCompleado timestamp,
        fechaIngreso timestamp,
        primary key (id)
    );

    create table MedioDePago (
        id varchar(255) not null,
        activo boolean not null,
        clave varchar(255),
        cuenta varchar(255),
        nombre varchar(255),
        usuario varchar(255),
        primary key (id)
    );

    create table Pasaje (
        id varchar(255) not null,
        ciPersona varchar(255),
        eliminado boolean,
        fechaCompra timestamp,
        pago boolean,
        usado boolean,
        comprador_id varchar(255),
        destino_id varchar(255),
        origen_id varchar(255),
        precio_id varchar(255),
        vendedor_id varchar(255),
        viaje_id varchar(255),
        primary key (id)
    );

    create table Perfil (
        id varchar(255) not null,
        modulo1 boolean not null,
        modulo2 boolean not null,
        modulo3 boolean not null,
        modulo4 boolean not null,
        modulo5 boolean not null,
        modulo6 boolean not null,
        modulo7 boolean not null,
        modulo8 boolean not null,
        primary key (id)
    );

    create table Perfil_Persona (
        Perfil_id varchar(255) not null,
        empleados_id varchar(255) not null
    );

    create table Persona (
        DTYPE varchar(31) not null,
        id varchar(255) not null,
        apellido varchar(255),
        eliminado boolean not null,
        email varchar(255),
        fechaNacimiento date,
        clave varchar(255),
        idRedSocial varchar(255),
        nombreAMostrar varchar(255),
        redSocialUsada varchar(255),
        idEmpleadoLdap varchar(255),
        cuponera_id varchar(255),
        perfil_id varchar(255),
        primary key (id)
    );

    create table Persona_Encomienda (
        Persona_id varchar(255) not null,
        encomiendas_id varchar(255) not null
    );

    create table Persona_telefonosContacto (
        Persona_id varchar(255) not null,
        telefono varchar(255)
    );

    create table Precio (
        id varchar(255) not null,
        monto float4 not null,
        destino_id varchar(255),
        origen_id varchar(255),
        primary key (id)
    );

    create table PuntoRecorrido (
        DTYPE varchar(31) not null,
        id varchar(255) not null,
        eliminado boolean,
        nombre varchar(255),
        ubicacionMapa varchar(255),
        aceptaEncomiendas boolean,
        primary key (id)
    );

    create table Recorrido (
        id varchar(255) not null,
        eliminado boolean,
        nombre varchar(255),
        primary key (id)
    );

    create table Recorrido_GrupoHorario (
        Recorrido_id varchar(255) not null,
        horarios_id varchar(255) not null
    );

    create table Recorrido_Precio (
        Recorrido_id varchar(255) not null,
        precios_id varchar(255) not null
    );

    create table Recorrido_PuntoRecorrido (
        Recorrido_id varchar(255) not null,
        puntosDeRecorrido_id varchar(255) not null
    );

    create table ReglaCobroEncomienda (
        id varchar(255) not null,
        nombre varchar(255),
        operador varchar(255),
        precio float4 not null,
        valor int4,
        primary key (id)
    );

    create table Reserva (
        id varchar(255) not null,
        ciPersona varchar(255),
        eliminada boolean not null,
        fechaReserva timestamp,
        utilizada boolean not null,
        destino_id varchar(255),
        empleado_id varchar(255),
        origen_id varchar(255),
        precio_id varchar(255),
        usuarioReserva_id varchar(255),
        viaje_id varchar(255),
        primary key (id)
    );

    create table Terminal_mailsDeContacto (
        Terminal_id varchar(255) not null,
        email varchar(255)
    );

    create table Terminal_telefonosContacto (
        Terminal_id varchar(255) not null,
        telefono varchar(255)
    );

    create table Usuario_notificaciones (
        Usuario_id varchar(255) not null,
        fecha timestamp,
        mensaje varchar(255)
    );

    create table Vehiculo (
        id varchar(255) not null,
        anioFabricacion int4,
        cantidadAsientos int4,
        conGuarda boolean,
        eliminado boolean,
        fechaAlta date,
        marca varchar(255),
        matricula varchar(255),
        modelo varchar(255),
        numeroVehiculo varchar(255),
        primary key (id)
    );

    create table Vehiculo_MantenimientoVehiculo (
        Vehiculo_id varchar(255) not null,
        mantenimientos_id varchar(255) not null
    );

    create table Viaje (
        id varchar(255) not null,
        coche bytea,
        fechaSalida timestamp,
        horario_id varchar(255),
        recorrido_id varchar(255),
        primary key (id)
    );

    create table Viaje_Encomienda (
        Viaje_id varchar(255) not null,
        encomiendas_id varchar(255) not null
    );

    create table Viaje_Persona (
        Viaje_id varchar(255) not null,
        empleados_id varchar(255) not null
    );

    create table Viaje_Reserva (
        Viaje_id varchar(255) not null,
        reservas_id varchar(255) not null
    );

    create table dias (
        GrupoHorarioId varchar(255) not null,
        diasSemana varchar(255)
    );

    alter table Encomienda_HistorialEstadosEncomienda 
        add constraint UK_o4ifqp9wtuyh6yyrj2y7dtunw  unique (estados_id);

    alter table GrupoHorario_Horario 
        add constraint UK_i8vwfxybh0quda0tavoaodq1q  unique (horarios_id);

    alter table Perfil_Persona 
        add constraint UK_72x6tlk8weghwf7fg7kwv6ggo  unique (empleados_id);

    alter table Persona_Encomienda 
        add constraint UK_l9o797tyjqhsyr852e79xvi4p  unique (encomiendas_id);

    alter table Recorrido_GrupoHorario 
        add constraint UK_3irwqpu998d89noj694x1aswh  unique (horarios_id);

    alter table Recorrido_Precio 
        add constraint UK_b0188ldsu72xkho27163gi1vr  unique (precios_id);

    alter table Recorrido_PuntoRecorrido 
        add constraint UK_ee5p44tsn9dco5l0dxf6u2p0o  unique (puntosDeRecorrido_id);

    alter table Vehiculo_MantenimientoVehiculo 
        add constraint UK_myj2cqpd205do48yrvsg77nnn  unique (mantenimientos_id);

    alter table Viaje_Encomienda 
        add constraint UK_osy1jnerltxq3sxf3fignr5fy  unique (encomiendas_id);

    alter table Viaje_Persona 
        add constraint UK_bc581nw2fuo8wooi6xklfjrg8  unique (empleados_id);

    alter table Viaje_Reserva 
        add constraint UK_nhxrsaq5q7iaebnhe0csko754  unique (reservas_id);

    alter table ConfiguracionEmpresa_emails 
        add constraint FK_1gy1wh0twl8d5pdpmkbc35rwc 
        foreign key (ConfiguracionEmpresa_id) 
        references ConfiguracionEmpresa;

    alter table ConfiguracionEmpresa_telefonos 
        add constraint FK_eq1skmvw0ahcnj2jjcg6jtqsv 
        foreign key (ConfiguracionEmpresa_id) 
        references ConfiguracionEmpresa;

    alter table DiasGruposHorarios 
        add constraint FK_j0fo4hou9o2cjpvscwql9otx8 
        foreign key (GrupoHorarioId) 
        references GrupoHorario;

    alter table Encomienda 
        add constraint FK_lu2t6lvkhos8kqv2mvkqqk5cl 
        foreign key (destino_id) 
        references PuntoRecorrido;

    alter table Encomienda 
        add constraint FK_sw3mg6bcaqxiehk012t7ljbkd 
        foreign key (emisor_id) 
        references Persona;

    alter table Encomienda 
        add constraint FK_m385yqv0o0i48aa1eghl2jqx6 
        foreign key (estadoActual_id) 
        references EstadosEncomienda;

    alter table Encomienda 
        add constraint FK_5x2u7bttkbkm5f80dm56pf59s 
        foreign key (origen_id) 
        references PuntoRecorrido;

    alter table Encomienda 
        add constraint FK_70r54agnflhmfrifumhap86xd 
        foreign key (receptor_id) 
        references Persona;

    alter table Encomienda 
        add constraint FK_5tvkuor274t22xm4ph1dp1aqb 
        foreign key (reglaCobro_id) 
        references ReglaCobroEncomienda;

    alter table Encomienda 
        add constraint FK_k9y4bsstq94qvbkstxqpm4itd 
        foreign key (viajeAsignado_id) 
        references Viaje;

    alter table Encomienda_HistorialEstadosEncomienda 
        add constraint FK_o4ifqp9wtuyh6yyrj2y7dtunw 
        foreign key (estados_id) 
        references HistorialEstadosEncomienda;

    alter table Encomienda_HistorialEstadosEncomienda 
        add constraint FK_qtqqa3mc5ov9io4i2w07unpq9 
        foreign key (Encomienda_id) 
        references Encomienda;

    alter table GrupoHorario_Horario 
        add constraint FK_i8vwfxybh0quda0tavoaodq1q 
        foreign key (horarios_id) 
        references Horario;

    alter table GrupoHorario_Horario 
        add constraint FK_hdk3ibt2x2e3u7s8kr5tgxcwn 
        foreign key (GrupoHorario_id) 
        references GrupoHorario;

    alter table HistorialEstadosEncomienda 
        add constraint FK_iv2bin3afihjpwyullr559i59 
        foreign key (estado_id) 
        references EstadosEncomienda;

    alter table Pasaje 
        add constraint FK_qypdlnb5jm65d9i5d7724ic2y 
        foreign key (comprador_id) 
        references Persona;

    alter table Pasaje 
        add constraint FK_osxop9oh3av3oyg6l2phjie6e 
        foreign key (destino_id) 
        references PuntoRecorrido;

    alter table Pasaje 
        add constraint FK_tgrk1toib3l43ymmcp3rllen9 
        foreign key (origen_id) 
        references PuntoRecorrido;

    alter table Pasaje 
        add constraint FK_lrv16svbya50dn4j8fyjg4rxp 
        foreign key (precio_id) 
        references Precio;

    alter table Pasaje 
        add constraint FK_8eddxbxbrcyl1sjjs5b9bi8s9 
        foreign key (vendedor_id) 
        references Persona;

    alter table Pasaje 
        add constraint FK_45tgxw544atj8lq6oyo9ly3kg 
        foreign key (viaje_id) 
        references Viaje;

    alter table Perfil_Persona 
        add constraint FK_72x6tlk8weghwf7fg7kwv6ggo 
        foreign key (empleados_id) 
        references Persona;

    alter table Perfil_Persona 
        add constraint FK_3uqsd0g7qbuuatp8fyuomq0vj 
        foreign key (Perfil_id) 
        references Perfil;

    alter table Persona 
        add constraint FK_3mmokvoe2m3hovhc6bb603if8 
        foreign key (cuponera_id) 
        references Cuponera;

    alter table Persona 
        add constraint FK_nsjd0asvtjy77jmuqukhdqa8a 
        foreign key (perfil_id) 
        references Perfil;

    alter table Persona_Encomienda 
        add constraint FK_l9o797tyjqhsyr852e79xvi4p 
        foreign key (encomiendas_id) 
        references Encomienda;

    alter table Persona_Encomienda 
        add constraint FK_brofvaqxcvhk5v4beg5rsl4bm 
        foreign key (Persona_id) 
        references Persona;

    alter table Persona_telefonosContacto 
        add constraint FK_64rj9xfvsrdxq0ohep3u85q54 
        foreign key (Persona_id) 
        references Persona;

    alter table Precio 
        add constraint FK_syilby562ubf2nf5b3f25f4bw 
        foreign key (destino_id) 
        references PuntoRecorrido;

    alter table Precio 
        add constraint FK_rmk9yovcmawvj5nd0mloeipoj 
        foreign key (origen_id) 
        references PuntoRecorrido;

    alter table Recorrido_GrupoHorario 
        add constraint FK_3irwqpu998d89noj694x1aswh 
        foreign key (horarios_id) 
        references GrupoHorario;

    alter table Recorrido_GrupoHorario 
        add constraint FK_gjqvoynoi8mg61x1wf56vckhw 
        foreign key (Recorrido_id) 
        references Recorrido;

    alter table Recorrido_Precio 
        add constraint FK_b0188ldsu72xkho27163gi1vr 
        foreign key (precios_id) 
        references Precio;

    alter table Recorrido_Precio 
        add constraint FK_qq2b4r9uw7ovs7acdhnbh74po 
        foreign key (Recorrido_id) 
        references Recorrido;

    alter table Recorrido_PuntoRecorrido 
        add constraint FK_ee5p44tsn9dco5l0dxf6u2p0o 
        foreign key (puntosDeRecorrido_id) 
        references PuntoRecorrido;

    alter table Recorrido_PuntoRecorrido 
        add constraint FK_5fo8vfw5v601xd5ffw17rkfqs 
        foreign key (Recorrido_id) 
        references Recorrido;

    alter table Reserva 
        add constraint FK_p6k2oyyml1fny5w4on9compj3 
        foreign key (destino_id) 
        references PuntoRecorrido;

    alter table Reserva 
        add constraint FK_7cv1rsnw8180t5ixp1nk0gbsn 
        foreign key (empleado_id) 
        references Persona;

    alter table Reserva 
        add constraint FK_dhrmid2rwsgmg6wfqy8oekua3 
        foreign key (origen_id) 
        references PuntoRecorrido;

    alter table Reserva 
        add constraint FK_a585lpkv2x3oq2xo16iijouu7 
        foreign key (precio_id) 
        references Precio;

    alter table Reserva 
        add constraint FK_gj6mwu7px9cdeq4spnm660su2 
        foreign key (usuarioReserva_id) 
        references Persona;

    alter table Reserva 
        add constraint FK_km77kynwhjjp437juk8ln5n6n 
        foreign key (viaje_id) 
        references Viaje;

    alter table Terminal_mailsDeContacto 
        add constraint FK_jrq99b4vla9j195p014p9r1bk 
        foreign key (Terminal_id) 
        references PuntoRecorrido;

    alter table Terminal_telefonosContacto 
        add constraint FK_pcss06xxddygc7c2vek340hkc 
        foreign key (Terminal_id) 
        references PuntoRecorrido;

    alter table Usuario_notificaciones 
        add constraint FK_ta692fdsg4mr0iaige255tfff 
        foreign key (Usuario_id) 
        references Persona;

    alter table Vehiculo_MantenimientoVehiculo 
        add constraint FK_myj2cqpd205do48yrvsg77nnn 
        foreign key (mantenimientos_id) 
        references MantenimientoVehiculo;

    alter table Vehiculo_MantenimientoVehiculo 
        add constraint FK_skcvsf2gy026guw5nom5jcuwf 
        foreign key (Vehiculo_id) 
        references Vehiculo;

    alter table Viaje 
        add constraint FK_l6exsxsgca9wo889gpl7os1ff 
        foreign key (horario_id) 
        references Horario;

    alter table Viaje 
        add constraint FK_s7a40spa1k4vgmf1i42boyb46 
        foreign key (recorrido_id) 
        references Recorrido;

    alter table Viaje_Encomienda 
        add constraint FK_osy1jnerltxq3sxf3fignr5fy 
        foreign key (encomiendas_id) 
        references Encomienda;

    alter table Viaje_Encomienda 
        add constraint FK_826g02p3v2vuyvraes4lb84sf 
        foreign key (Viaje_id) 
        references Viaje;

    alter table Viaje_Persona 
        add constraint FK_bc581nw2fuo8wooi6xklfjrg8 
        foreign key (empleados_id) 
        references Persona;

    alter table Viaje_Persona 
        add constraint FK_ot5d3wyxfudo29ub3a8y100eu 
        foreign key (Viaje_id) 
        references Viaje;

    alter table Viaje_Reserva 
        add constraint FK_nhxrsaq5q7iaebnhe0csko754 
        foreign key (reservas_id) 
        references Reserva;

    alter table Viaje_Reserva 
        add constraint FK_hfinyo80vii4dt8h6nvdmp80l 
        foreign key (Viaje_id) 
        references Viaje;

    alter table dias 
        add constraint FK_hbh26sy6gwvgixbk380fa5im2 
        foreign key (GrupoHorarioId) 
        references GrupoHorario;
