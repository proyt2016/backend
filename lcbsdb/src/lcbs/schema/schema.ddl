 CREATE TABLE configuracionempresa (
    id character varying(255) NOT NULL,
    aceptacuponera boolean,
    activo boolean,
    baseldap character varying(255),
    colorboton character varying(255),
    colorfondolista character varying(255),
    colorfondosdepantalla character varying(255),
    colorletras character varying(255),
    colortextolista character varying(255),
    colortitulo character varying(255),
    css text,
    diascreacionviaje integer,
    iconoempresa character varying(255),
    nombre character varying(255),
    pagoonlinecoche boolean,
    reservapasajes boolean,
    stripeprivatekey character varying(255),
    stripepublickey character varying(255),
    trasferirpasajes boolean,
    ultimacreaciondeviajes timestamp without time zone,
    urlacceso character varying(255),
    urlldap character varying(255),
    validesreservashoras integer
);


ALTER TABLE configuracionempresa OWNER TO lcbsadmin;


CREATE TABLE configuracionempresa_emails (
    configuracionempresa_id character varying(255) NOT NULL,
    descripcion character varying(255),
    email character varying(255),
    list_index integer NOT NULL
);


ALTER TABLE configuracionempresa_emails OWNER TO lcbsadmin;


CREATE TABLE configuracionempresa_telefonos (
    configuracionempresa_id character varying(255) NOT NULL,
    descripcion character varying(255),
    telefono character varying(255),
    list_index integer NOT NULL
);


ALTER TABLE configuracionempresa_telefonos OWNER TO lcbsadmin;


CREATE TABLE cuponera (
    id character varying(255) NOT NULL,
    saldo real
);


ALTER TABLE cuponera OWNER TO lcbsadmin;


CREATE TABLE dias (
    grupohorarioid character varying(255) NOT NULL,
    diassemana character varying(255),
    list_index integer NOT NULL
);


ALTER TABLE dias OWNER TO lcbsadmin;


CREATE TABLE diasgruposhorarios (
    grupohorarioid character varying(255) NOT NULL,
    diasespecificos timestamp without time zone,
    list_index integer NOT NULL
);


ALTER TABLE diasgruposhorarios OWNER TO lcbsadmin;


CREATE TABLE encomienda (
    id character varying(255) NOT NULL,
    codigoencomienda integer NOT NULL,
    ciemisor character varying(255),
    cireceptor character varying(255),
    direccionreceptor character varying(255),
    eliminada boolean,
    fechaentrega timestamp without time zone,
    fechaingreso timestamp without time zone,
    monto real,
    paga boolean,
    pagareceptor boolean,
    precio real,
    retiraensucursal boolean,
    desctelemisor character varying(255),
    telemisor character varying(255),
    desctelreceptor character varying(255),
    telreceptor character varying(255),
    cocheasignado_id character varying(255),
    destino_id character varying(255),
    emisor_id character varying(255),
    estadoactual_id character varying(255),
    origen_id character varying(255),
    receptor_id character varying(255),
    reglacobro_id character varying(255),
    viajeasignado_id character varying(255)
);


ALTER TABLE encomienda OWNER TO lcbsadmin;


CREATE TABLE encomienda_historialestadosencomienda (
    encomienda_id character varying(255) NOT NULL,
    encomienda_codigoencomienda integer NOT NULL,
    estados_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE encomienda_historialestadosencomienda OWNER TO lcbsadmin;


CREATE TABLE estadosencomienda (
    id character varying(255) NOT NULL,
    nombre character varying(255)
);


ALTER TABLE estadosencomienda OWNER TO lcbsadmin;


CREATE TABLE grupohorario (
    id character varying(255) NOT NULL,
    nombre character varying(255),
    tipo character varying(255)
);


ALTER TABLE grupohorario OWNER TO lcbsadmin;


CREATE TABLE grupohorario_horario (
    grupohorario_id character varying(255) NOT NULL,
    horarios_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE grupohorario_horario OWNER TO lcbsadmin;


CREATE TABLE historialestadosencomienda (
    id character varying(255) NOT NULL,
    fecha timestamp without time zone,
    estado_id character varying(255)
);


ALTER TABLE historialestadosencomienda OWNER TO lcbsadmin;


CREATE TABLE horario (
    id character varying(255) NOT NULL,
    nombre character varying(255)
);


ALTER TABLE horario OWNER TO lcbsadmin;


CREATE TABLE mantenimientovehiculo (
    id character varying(255) NOT NULL,
    costo real,
    descripcioncompleta character varying(255),
    descripcionreducida character varying(255),
    fechacompleado timestamp without time zone,
    fechaingreso timestamp without time zone
);


ALTER TABLE mantenimientovehiculo OWNER TO lcbsadmin;


CREATE TABLE mediodepago (
    id character varying(255) NOT NULL,
    activo boolean,
    clave character varying(255),
    cuenta character varying(255),
    nombre character varying(255),
    usuario character varying(255)
);


ALTER TABLE mediodepago OWNER TO lcbsadmin;


CREATE TABLE pasaje (
    id character varying(255) NOT NULL,
    codigopasaje integer NOT NULL,
    cipersona character varying(255),
    eliminado boolean,
    fechacompra timestamp without time zone,
    pago boolean,
    usado boolean,
    comprador_id character varying(255),
    destino_id character varying(255),
    origen_id character varying(255),
    precio_id character varying(255),
    vendedor_id character varying(255),
    viaje_id character varying(255)
);


ALTER TABLE pasaje OWNER TO lcbsadmin;


CREATE TABLE perfil (
    id character varying(255) NOT NULL,
    configuracionempresa boolean,
    gestionencomiendas boolean,
    gestionpasajes boolean,
    gestionreportes boolean,
    mantenimientoflota boolean,
    nombreperfil character varying(255)
);


ALTER TABLE perfil OWNER TO lcbsadmin;


CREATE TABLE perfil_persona (
    perfil_id character varying(255) NOT NULL,
    empleados_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE perfil_persona OWNER TO lcbsadmin;


CREATE TABLE persona (
    dtype character varying(31) NOT NULL,
    id character varying(255) NOT NULL,
    apellido character varying(255),
    clave character varying(255),
    eliminado boolean,
    descripcion character varying(255),
    email character varying(255),
    fechanacimiento date,
    nombrepila character varying(255),
    idredsocial character varying(255),
    redsocialusada character varying(255),
    stripecustomerid character varying(255),
    ultimoscuatrodigitos integer,
    idempleadoldap character varying(255),
    cuponera_id character varying(255),
    perfil_id character varying(255)
);


ALTER TABLE persona OWNER TO lcbsadmin;


CREATE TABLE persona_encomienda (
    usuario_id character varying(255) NOT NULL,
    encomiendas_id character varying(255) NOT NULL,
    encomiendas_codigoencomienda integer NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE persona_encomienda OWNER TO lcbsadmin;


CREATE TABLE persona_telefonoscontacto (
    persona_id character varying(255) NOT NULL,
    descripcion character varying(255),
    telefono character varying(255),
    list_index integer NOT NULL
);


ALTER TABLE persona_telefonoscontacto OWNER TO lcbsadmin;


CREATE TABLE precio (
    id character varying(255) NOT NULL,
    monto real,
    destino_id character varying(255),
    origen_id character varying(255)
);


ALTER TABLE precio OWNER TO lcbsadmin;


CREATE TABLE puntorecorrido (
    dtype character varying(31) NOT NULL,
    id character varying(255) NOT NULL,
    eliminado boolean,
    nombre character varying(255),
    ubicacionmapa character varying(255),
    aceptaencomiendas boolean
);


ALTER TABLE puntorecorrido OWNER TO lcbsadmin;


CREATE TABLE recorrido (
    id character varying(255) NOT NULL,
    eliminado boolean,
    nombre character varying(255)
);


ALTER TABLE recorrido OWNER TO lcbsadmin;


CREATE TABLE recorrido_grupohorario (
    recorrido_id character varying(255) NOT NULL,
    horarios_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE recorrido_grupohorario OWNER TO lcbsadmin;


CREATE TABLE recorrido_precio (
    recorrido_id character varying(255) NOT NULL,
    precios_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE recorrido_precio OWNER TO lcbsadmin;


CREATE TABLE recorrido_puntorecorrido (
    recorrido_id character varying(255) NOT NULL,
    puntosderecorrido_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE recorrido_puntorecorrido OWNER TO lcbsadmin;


CREATE TABLE reglacobroencomienda (
    id character varying(255) NOT NULL,
    nombre character varying(255),
    precioexactoocalculo boolean
);


ALTER TABLE reglacobroencomienda OWNER TO lcbsadmin;


CREATE TABLE reglacobroencomienda_reglacobroencomiendacriteria (
    reglacobroencomienda_id character varying(255) NOT NULL,
    criterias_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE reglacobroencomienda_reglacobroencomiendacriteria OWNER TO lcbsadmin;


CREATE TABLE reglacobroencomiendacriteria (
    id character varying(255) NOT NULL,
    operador character varying(255),
    precio real,
    valor integer
);


ALTER TABLE reglacobroencomiendacriteria OWNER TO lcbsadmin;


CREATE TABLE reserva (
    id character varying(255) NOT NULL,
    cipersona character varying(255),
    eliminada boolean,
    fechareserva timestamp without time zone,
    utilizada boolean,
    destino_id character varying(255),
    empleado_id character varying(255),
    origen_id character varying(255),
    precio_id character varying(255),
    usuarioreserva_id character varying(255),
    viaje_id character varying(255)
);


ALTER TABLE reserva OWNER TO lcbsadmin;


CREATE TABLE terminal_mailsdecontacto (
    terminal_id character varying(255) NOT NULL,
    descripcion character varying(255),
    email character varying(255),
    list_index integer NOT NULL
);


ALTER TABLE terminal_mailsdecontacto OWNER TO lcbsadmin;


CREATE TABLE terminal_telefonoscontacto (
    terminal_id character varying(255) NOT NULL,
    descripcion character varying(255),
    telefono character varying(255),
    list_index integer NOT NULL
);


ALTER TABLE terminal_telefonoscontacto OWNER TO lcbsadmin;


CREATE TABLE usuario_notificaciones (
    usuario_id character varying(255) NOT NULL,
    fecha timestamp without time zone,
    mensaje character varying(255),
    list_index integer NOT NULL
);


ALTER TABLE usuario_notificaciones OWNER TO lcbsadmin;


CREATE TABLE vehiculo (
    id character varying(255) NOT NULL,
    aniofabricacion integer,
    cantidadasientos integer,
    conguarda boolean,
    eliminado boolean,
    enmantenimiento boolean,
    fechaalta date,
    marca character varying(255),
    matricula character varying(255),
    modelo character varying(255),
    numerovehiculo character varying(255)
);


ALTER TABLE vehiculo OWNER TO lcbsadmin;


CREATE TABLE vehiculo_encomienda (
    vehiculo_id character varying(255) NOT NULL,
    encomiendas_id character varying(255) NOT NULL,
    encomiendas_codigoencomienda integer NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE vehiculo_encomienda OWNER TO lcbsadmin;


CREATE TABLE vehiculo_mantenimientovehiculo (
    vehiculo_id character varying(255) NOT NULL,
    mantenimientos_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE vehiculo_mantenimientovehiculo OWNER TO lcbsadmin;


CREATE TABLE viaje (
    id character varying(255) NOT NULL,
    fechasalida timestamp without time zone,
    horario_id character varying(255),
    recorrido_id character varying(255)
);


ALTER TABLE viaje OWNER TO lcbsadmin;


CREATE TABLE viaje_persona (
    viaje_id character varying(255) NOT NULL,
    empleados_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE viaje_persona OWNER TO lcbsadmin;


CREATE TABLE viaje_reserva (
    viaje_id character varying(255) NOT NULL,
    reservas_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE viaje_reserva OWNER TO lcbsadmin;


CREATE TABLE viaje_vehiculo (
    viaje_id character varying(255) NOT NULL,
    coches_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE viaje_vehiculo OWNER TO lcbsadmin;


ALTER TABLE ONLY configuracionempresa_emails
    ADD CONSTRAINT configuracionempresa_emails_pkey PRIMARY KEY (configuracionempresa_id, list_index);



ALTER TABLE ONLY configuracionempresa
    ADD CONSTRAINT configuracionempresa_pkey PRIMARY KEY (id);



ALTER TABLE ONLY configuracionempresa_telefonos
    ADD CONSTRAINT configuracionempresa_telefonos_pkey PRIMARY KEY (configuracionempresa_id, list_index);



ALTER TABLE ONLY cuponera
    ADD CONSTRAINT cuponera_pkey PRIMARY KEY (id);



ALTER TABLE ONLY dias
    ADD CONSTRAINT dias_pkey PRIMARY KEY (grupohorarioid, list_index);



ALTER TABLE ONLY diasgruposhorarios
    ADD CONSTRAINT diasgruposhorarios_pkey PRIMARY KEY (grupohorarioid, list_index);



ALTER TABLE ONLY encomienda_historialestadosencomienda
    ADD CONSTRAINT encomienda_historialestadosencomienda_pkey PRIMARY KEY (encomienda_id, encomienda_codigoencomienda, list_index);



ALTER TABLE ONLY encomienda
    ADD CONSTRAINT encomienda_pkey PRIMARY KEY (id, codigoencomienda);



ALTER TABLE ONLY estadosencomienda
    ADD CONSTRAINT estadosencomienda_pkey PRIMARY KEY (id);



ALTER TABLE ONLY grupohorario_horario
    ADD CONSTRAINT grupohorario_horario_pkey PRIMARY KEY (grupohorario_id, list_index);



ALTER TABLE ONLY grupohorario
    ADD CONSTRAINT grupohorario_pkey PRIMARY KEY (id);



ALTER TABLE ONLY historialestadosencomienda
    ADD CONSTRAINT historialestadosencomienda_pkey PRIMARY KEY (id);



ALTER TABLE ONLY horario
    ADD CONSTRAINT horario_pkey PRIMARY KEY (id);



ALTER TABLE ONLY mantenimientovehiculo
    ADD CONSTRAINT mantenimientovehiculo_pkey PRIMARY KEY (id);



ALTER TABLE ONLY mediodepago
    ADD CONSTRAINT mediodepago_pkey PRIMARY KEY (id);



ALTER TABLE ONLY pasaje
    ADD CONSTRAINT pasaje_pkey PRIMARY KEY (id, codigopasaje);



ALTER TABLE ONLY perfil_persona
    ADD CONSTRAINT perfil_persona_pkey PRIMARY KEY (perfil_id, list_index);



ALTER TABLE ONLY perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (id);



ALTER TABLE ONLY persona_encomienda
    ADD CONSTRAINT persona_encomienda_pkey PRIMARY KEY (usuario_id, list_index);



ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id);



ALTER TABLE ONLY persona_telefonoscontacto
    ADD CONSTRAINT persona_telefonoscontacto_pkey PRIMARY KEY (persona_id, list_index);



ALTER TABLE ONLY precio
    ADD CONSTRAINT precio_pkey PRIMARY KEY (id);



ALTER TABLE ONLY puntorecorrido
    ADD CONSTRAINT puntorecorrido_pkey PRIMARY KEY (id);



ALTER TABLE ONLY recorrido_grupohorario
    ADD CONSTRAINT recorrido_grupohorario_pkey PRIMARY KEY (recorrido_id, list_index);



ALTER TABLE ONLY recorrido
    ADD CONSTRAINT recorrido_pkey PRIMARY KEY (id);



ALTER TABLE ONLY recorrido_precio
    ADD CONSTRAINT recorrido_precio_pkey PRIMARY KEY (recorrido_id, list_index);



ALTER TABLE ONLY recorrido_puntorecorrido
    ADD CONSTRAINT recorrido_puntorecorrido_pkey PRIMARY KEY (recorrido_id, list_index);



ALTER TABLE ONLY reglacobroencomienda
    ADD CONSTRAINT reglacobroencomienda_pkey PRIMARY KEY (id);



ALTER TABLE ONLY reglacobroencomienda_reglacobroencomiendacriteria
    ADD CONSTRAINT reglacobroencomienda_reglacobroencomiendacriteria_pkey PRIMARY KEY (reglacobroencomienda_id, list_index);



ALTER TABLE ONLY reglacobroencomiendacriteria
    ADD CONSTRAINT reglacobroencomiendacriteria_pkey PRIMARY KEY (id);



ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_pkey PRIMARY KEY (id);



ALTER TABLE ONLY terminal_mailsdecontacto
    ADD CONSTRAINT terminal_mailsdecontacto_pkey PRIMARY KEY (terminal_id, list_index);



ALTER TABLE ONLY terminal_telefonoscontacto
    ADD CONSTRAINT terminal_telefonoscontacto_pkey PRIMARY KEY (terminal_id, list_index);



ALTER TABLE ONLY recorrido_grupohorario
    ADD CONSTRAINT uk_3irwqpu998d89noj694x1aswh UNIQUE (horarios_id);



ALTER TABLE ONLY perfil_persona
    ADD CONSTRAINT uk_72x6tlk8weghwf7fg7kwv6ggo UNIQUE (empleados_id);



ALTER TABLE ONLY reglacobroencomienda_reglacobroencomiendacriteria
    ADD CONSTRAINT uk_arpe4vnc33o327gr1s5t2ttmw UNIQUE (criterias_id);



ALTER TABLE ONLY recorrido_precio
    ADD CONSTRAINT uk_b0188ldsu72xkho27163gi1vr UNIQUE (precios_id);



ALTER TABLE ONLY viaje_persona
    ADD CONSTRAINT uk_bc581nw2fuo8wooi6xklfjrg8 UNIQUE (empleados_id);



ALTER TABLE ONLY grupohorario_horario
    ADD CONSTRAINT uk_i8vwfxybh0quda0tavoaodq1q UNIQUE (horarios_id);



ALTER TABLE ONLY vehiculo_mantenimientovehiculo
    ADD CONSTRAINT uk_myj2cqpd205do48yrvsg77nnn UNIQUE (mantenimientos_id);



ALTER TABLE ONLY viaje_reserva
    ADD CONSTRAINT uk_nhxrsaq5q7iaebnhe0csko754 UNIQUE (reservas_id);



ALTER TABLE ONLY encomienda_historialestadosencomienda
    ADD CONSTRAINT uk_o4ifqp9wtuyh6yyrj2y7dtunw UNIQUE (estados_id);



ALTER TABLE ONLY persona_encomienda
    ADD CONSTRAINT uk_ob0glgv6x5261k1a82wbu9r4a UNIQUE (encomiendas_id, encomiendas_codigoencomienda);



ALTER TABLE ONLY vehiculo_encomienda
    ADD CONSTRAINT uk_p1qpltmjq3qchbxp10kgg4x0q UNIQUE (encomiendas_id, encomiendas_codigoencomienda);



ALTER TABLE ONLY usuario_notificaciones
    ADD CONSTRAINT usuario_notificaciones_pkey PRIMARY KEY (usuario_id, list_index);



ALTER TABLE ONLY vehiculo_encomienda
    ADD CONSTRAINT vehiculo_encomienda_pkey PRIMARY KEY (vehiculo_id, list_index);



ALTER TABLE ONLY vehiculo_mantenimientovehiculo
    ADD CONSTRAINT vehiculo_mantenimientovehiculo_pkey PRIMARY KEY (vehiculo_id, list_index);



ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT vehiculo_pkey PRIMARY KEY (id);



ALTER TABLE ONLY viaje_persona
    ADD CONSTRAINT viaje_persona_pkey PRIMARY KEY (viaje_id, list_index);



ALTER TABLE ONLY viaje
    ADD CONSTRAINT viaje_pkey PRIMARY KEY (id);



ALTER TABLE ONLY viaje_reserva
    ADD CONSTRAINT viaje_reserva_pkey PRIMARY KEY (viaje_id, list_index);



ALTER TABLE ONLY viaje_vehiculo
    ADD CONSTRAINT viaje_vehiculo_pkey PRIMARY KEY (viaje_id, list_index);



ALTER TABLE ONLY grupohorario_horario
    ADD CONSTRAINT fk1cyopb1pfn3a1q1vyvtc5n2a3 FOREIGN KEY (grupohorario_id) REFERENCES grupohorario(id);



ALTER TABLE ONLY reserva
    ADD CONSTRAINT fk1dy6gx31gvo63wox77vdlr80d FOREIGN KEY (destino_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY recorrido_grupohorario
    ADD CONSTRAINT fk1rr26mlyl638rck1eph61krsq FOREIGN KEY (horarios_id) REFERENCES grupohorario(id);



ALTER TABLE ONLY encomienda_historialestadosencomienda
    ADD CONSTRAINT fk2b1rqix9pkvvh8le9c6h6caw8 FOREIGN KEY (encomienda_id, encomienda_codigoencomienda) REFERENCES encomienda(id, codigoencomienda);



ALTER TABLE ONLY reserva
    ADD CONSTRAINT fk3tcemn8ibirmyvmtolpnkpl15 FOREIGN KEY (origen_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY dias
    ADD CONSTRAINT fk44ae9x4ajct36d3lk81ch8s9m FOREIGN KEY (grupohorarioid) REFERENCES grupohorario(id);



ALTER TABLE ONLY perfil_persona
    ADD CONSTRAINT fk4likmo431b1vjm7oigsl2rwqk FOREIGN KEY (empleados_id) REFERENCES persona(id);



ALTER TABLE ONLY viaje_persona
    ADD CONSTRAINT fk4xn4rtjc98fyux3tovm5yipji FOREIGN KEY (viaje_id) REFERENCES viaje(id);



ALTER TABLE ONLY vehiculo_mantenimientovehiculo
    ADD CONSTRAINT fk75qefbakv93w3acylg5nt410q FOREIGN KEY (mantenimientos_id) REFERENCES mantenimientovehiculo(id);



ALTER TABLE ONLY encomienda
    ADD CONSTRAINT fk7nlpyvdcc7ux28bcs7g2v2lwv FOREIGN KEY (reglacobro_id) REFERENCES reglacobroencomienda(id);



ALTER TABLE ONLY pasaje
    ADD CONSTRAINT fk84ju01i74icxfr4rt2o8lmey5 FOREIGN KEY (vendedor_id) REFERENCES persona(id);



ALTER TABLE ONLY viaje_vehiculo
    ADD CONSTRAINT fk8cxbf5ttw8s5nuyhtxpp5v672 FOREIGN KEY (coches_id) REFERENCES vehiculo(id);



ALTER TABLE ONLY terminal_mailsdecontacto
    ADD CONSTRAINT fk8idds4kvvca8ds54b54i5igf5 FOREIGN KEY (terminal_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY persona
    ADD CONSTRAINT fk8l6bc0b161m6u65tpyn74pwmk FOREIGN KEY (perfil_id) REFERENCES perfil(id);



ALTER TABLE ONLY pasaje
    ADD CONSTRAINT fkadlybik3bbin8blyhyrk1ca2x FOREIGN KEY (comprador_id) REFERENCES persona(id);



ALTER TABLE ONLY usuario_notificaciones
    ADD CONSTRAINT fkaskrwwhftv4hqdadxdre4hohk FOREIGN KEY (usuario_id) REFERENCES persona(id);



ALTER TABLE ONLY recorrido_puntorecorrido
    ADD CONSTRAINT fkbdqtykjw051sc93h3qdlee9pd FOREIGN KEY (puntosderecorrido_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY persona_encomienda
    ADD CONSTRAINT fkbek63w7057p9fiayixcm6cqcu FOREIGN KEY (usuario_id) REFERENCES persona(id);



ALTER TABLE ONLY perfil_persona
    ADD CONSTRAINT fkbiy8i4lqst7aw41d7agjcgkhf FOREIGN KEY (perfil_id) REFERENCES perfil(id);



ALTER TABLE ONLY recorrido_precio
    ADD CONSTRAINT fkcepbfyow62myvwiragfelgghv FOREIGN KEY (recorrido_id) REFERENCES recorrido(id);



ALTER TABLE ONLY reglacobroencomienda_reglacobroencomiendacriteria
    ADD CONSTRAINT fkctsnhshcy4yy0shjltk0t571y FOREIGN KEY (reglacobroencomienda_id) REFERENCES reglacobroencomienda(id);



ALTER TABLE ONLY recorrido_grupohorario
    ADD CONSTRAINT fkcwp7c9lvgm27kj2gqmei5bqgt FOREIGN KEY (recorrido_id) REFERENCES recorrido(id);



ALTER TABLE ONLY reserva
    ADD CONSTRAINT fkevhpbu6mht53aotnvfyy5rntg FOREIGN KEY (usuarioreserva_id) REFERENCES persona(id);



ALTER TABLE ONLY terminal_telefonoscontacto
    ADD CONSTRAINT fkfgjvdh59pallrgs6ri0uqgkky FOREIGN KEY (terminal_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY recorrido_puntorecorrido
    ADD CONSTRAINT fkfmqpb2so6dksahp82hrl1qwdm FOREIGN KEY (recorrido_id) REFERENCES recorrido(id);



ALTER TABLE ONLY reglacobroencomienda_reglacobroencomiendacriteria
    ADD CONSTRAINT fkfpxn2m9katkfbk9e478uxwole FOREIGN KEY (criterias_id) REFERENCES reglacobroencomiendacriteria(id);



ALTER TABLE ONLY precio
    ADD CONSTRAINT fkg2n1ulm6xoybntytanqohqc5m FOREIGN KEY (origen_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY configuracionempresa_telefonos
    ADD CONSTRAINT fkg5uofhi1cswc81poii1gvd0n3 FOREIGN KEY (configuracionempresa_id) REFERENCES configuracionempresa(id);



ALTER TABLE ONLY reserva
    ADD CONSTRAINT fkg7s5u2p6qmk8i09is63kjjcd2 FOREIGN KEY (viaje_id) REFERENCES viaje(id);



ALTER TABLE ONLY persona
    ADD CONSTRAINT fkgay6gqg2ndwcc7nwrg7ydfnht FOREIGN KEY (cuponera_id) REFERENCES cuponera(id);



ALTER TABLE ONLY encomienda
    ADD CONSTRAINT fkgg9ayhfk4b5ecqq5bbh0iyuv1 FOREIGN KEY (destino_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY viaje_reserva
    ADD CONSTRAINT fkh8nvl9s263q1i9rff8e5ttylr FOREIGN KEY (viaje_id) REFERENCES viaje(id);



ALTER TABLE ONLY recorrido_precio
    ADD CONSTRAINT fkhqnjehhf54cwgh4dy7ld2d482 FOREIGN KEY (precios_id) REFERENCES precio(id);



ALTER TABLE ONLY pasaje
    ADD CONSTRAINT fki4atco3nerg3or6mr3l5fxrwa FOREIGN KEY (destino_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY pasaje
    ADD CONSTRAINT fkifcoej3ctn1craextk4f0c6sc FOREIGN KEY (origen_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY precio
    ADD CONSTRAINT fkiysfc29ourld6rox6ee9hsjyk FOREIGN KEY (destino_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY encomienda_historialestadosencomienda
    ADD CONSTRAINT fkj406jrseiphrc3ae5qcth1uup FOREIGN KEY (estados_id) REFERENCES historialestadosencomienda(id);



ALTER TABLE ONLY persona_encomienda
    ADD CONSTRAINT fkjimmraevpudwjgn93u7bffeto FOREIGN KEY (encomiendas_id, encomiendas_codigoencomienda) REFERENCES encomienda(id, codigoencomienda);



ALTER TABLE ONLY historialestadosencomienda
    ADD CONSTRAINT fkjkjxbs1ophkv0md3je43atefm FOREIGN KEY (estado_id) REFERENCES estadosencomienda(id);



ALTER TABLE ONLY viaje
    ADD CONSTRAINT fkjukb51oc98jxwyb3fvr59kl4n FOREIGN KEY (recorrido_id) REFERENCES recorrido(id);



ALTER TABLE ONLY viaje
    ADD CONSTRAINT fkk2pxxyxhm64f1bdcgaxeupc9u FOREIGN KEY (horario_id) REFERENCES horario(id);



ALTER TABLE ONLY persona_telefonoscontacto
    ADD CONSTRAINT fkk40w7lbc0hpiolhkh845krknh FOREIGN KEY (persona_id) REFERENCES persona(id);



ALTER TABLE ONLY vehiculo_mantenimientovehiculo
    ADD CONSTRAINT fkk8cyby29bqq3h2kous1yihdtp FOREIGN KEY (vehiculo_id) REFERENCES vehiculo(id);



ALTER TABLE ONLY pasaje
    ADD CONSTRAINT fkka66fsg7t708wnwt3vfugyivc FOREIGN KEY (precio_id) REFERENCES precio(id);



ALTER TABLE ONLY diasgruposhorarios
    ADD CONSTRAINT fkl2xrstmymc4hnolttc7g5t20n FOREIGN KEY (grupohorarioid) REFERENCES grupohorario(id);



ALTER TABLE ONLY reserva
    ADD CONSTRAINT fkloxv9lqeerovxbsu00ux0hc9o FOREIGN KEY (precio_id) REFERENCES precio(id);



ALTER TABLE ONLY encomienda
    ADD CONSTRAINT fkm2rbiwdbnc4ese65q603avlq FOREIGN KEY (receptor_id) REFERENCES persona(id);



ALTER TABLE ONLY vehiculo_encomienda
    ADD CONSTRAINT fkmylbx8oekv06tqi6xhic2u1lu FOREIGN KEY (vehiculo_id) REFERENCES vehiculo(id);



ALTER TABLE ONLY viaje_persona
    ADD CONSTRAINT fkndyqko0drn7t64khiu5tbugp9 FOREIGN KEY (empleados_id) REFERENCES persona(id);



ALTER TABLE ONLY vehiculo_encomienda
    ADD CONSTRAINT fknobn5sun1w69alr5cjtp040tm FOREIGN KEY (encomiendas_id, encomiendas_codigoencomienda) REFERENCES encomienda(id, codigoencomienda);



ALTER TABLE ONLY viaje_vehiculo
    ADD CONSTRAINT fknr4y4v0cfx6u06gw7fsy8cojl FOREIGN KEY (viaje_id) REFERENCES viaje(id);



ALTER TABLE ONLY viaje_reserva
    ADD CONSTRAINT fko11qfryjp9cuipejwultc8kfg FOREIGN KEY (reservas_id) REFERENCES reserva(id);



ALTER TABLE ONLY grupohorario_horario
    ADD CONSTRAINT fkp6xs8loi38ysmdmqjfu6h2d2g FOREIGN KEY (horarios_id) REFERENCES horario(id);



ALTER TABLE ONLY encomienda
    ADD CONSTRAINT fkq2toppcywxqwikjrhy3yqu9a4 FOREIGN KEY (origen_id) REFERENCES puntorecorrido(id);



ALTER TABLE ONLY pasaje
    ADD CONSTRAINT fkr0p6gsh3jvupyh930dgcns31y FOREIGN KEY (viaje_id) REFERENCES viaje(id);



ALTER TABLE ONLY encomienda
    ADD CONSTRAINT fkr5glnig2snpkq3x3m8t88bacx FOREIGN KEY (cocheasignado_id) REFERENCES vehiculo(id);



ALTER TABLE ONLY encomienda
    ADD CONSTRAINT fkrhqly5oh43m5qonjqqjchmjq9 FOREIGN KEY (estadoactual_id) REFERENCES estadosencomienda(id);



ALTER TABLE ONLY configuracionempresa_emails
    ADD CONSTRAINT fkrmrscppq0a4bd8ov2ajfjao54 FOREIGN KEY (configuracionempresa_id) REFERENCES configuracionempresa(id);



ALTER TABLE ONLY reserva
    ADD CONSTRAINT fksm6ebd7b2rlof3a3t2skfd6ax FOREIGN KEY (empleado_id) REFERENCES persona(id);



ALTER TABLE ONLY encomienda
    ADD CONSTRAINT fkta165wh9q7n089mxqd553wpgn FOREIGN KEY (viajeasignado_id) REFERENCES viaje(id);



ALTER TABLE ONLY encomienda
    ADD CONSTRAINT fkti4ao76euxqqxch93965kr0ox FOREIGN KEY (emisor_id) REFERENCES persona(id);




create sequence hibernate_sequence;

INSERT INTO estadosencomienda (id, nombre) VALUES ('9a265943-ab81-4a19-a752-03b2db475fed', 'Recibida');
INSERT INTO estadosencomienda (id, nombre) VALUES ('c1423557-e9fb-472a-92f6-023328107117', 'Enviada');
INSERT INTO estadosencomienda (id, nombre) VALUES ('f8be0436-76f0-44b0-88d8-4051bb844b41', 'Perdida');
INSERT INTO estadosencomienda (id, nombre) VALUES ('cc9bf2f4-e09a-4067-82ab-4bd4e800c7a3', 'En viaje');
INSERT INTO estadosencomienda (id, nombre) VALUES ('f75ebf4c-43a5-4331-91e6-bb16e94a42eb', 'Regresada');
INSERT INTO estadosencomienda (id, nombre) VALUES ('f7036bc6-a43e-4d14-ac9c-cb35f182235b', 'Entregada');




INSERT INTO perfil (id, gestionEncomiendas, gestionPasajes, ConfiguracionEmpresa, gestionReportes, mantenimientoFlota, nombreperfil) VALUES ('ecefa62b-185a-4e48-a019-f67521e2b9cd', true, true, true, true, true, 'Admin');
INSERT INTO perfil (id, gestionEncomiendas, gestionPasajes, ConfiguracionEmpresa, gestionReportes, mantenimientoFlota, nombreperfil) VALUES ('40b64aed-ae63-4908-914d-b011188c171a', true, true, false, false, false, 'Vendedor');
INSERT INTO perfil (id, gestionEncomiendas, gestionPasajes, ConfiguracionEmpresa, gestionReportes, mantenimientoFlota, nombreperfil) VALUES ('49b26bf8-1be4-4405-a0d7-0356f644440d', true, false, false, false, false, 'Encargado Encomiendas');
INSERT INTO perfil (id, gestionEncomiendas, gestionPasajes, ConfiguracionEmpresa, gestionReportes, mantenimientoFlota, nombreperfil) VALUES ('fb192132-7a5c-427a-9c55-bc4b339e1ffd', false, true, false, false, false, 'Guarda');
INSERT INTO perfil (id, gestionEncomiendas, gestionPasajes, ConfiguracionEmpresa, gestionReportes, mantenimientoFlota, nombreperfil) VALUES ('d4d86bd5-60e5-4b45-abd4-58ea63af3b84', false, false, false, true, true, 'Encargado Mantenimiento Vehiculos');

INSERT INTO persona (dtype, id, apellido, eliminado, descripcion, email, fechanacimiento, nombrepila, clave, idredsocial, redsocialusada, idempleadoldap, cuponera_id, perfil_id) VALUES ('Empleado', 'c726c21c-f2e4-4094-a9b6-bb5811cf2e0e', 'Admin', false, NULL, 'admin', NULL, 'System', 'a4fd8e6fa9fbf9a6f2c99e7b70aa9ef2', NULL, NULL, NULL, NULL, 'ecefa62b-185a-4e48-a019-f67521e2b9cd');

 
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', false, 'tres cruces', '-34.8940096615171,-56.16642236709595', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '4ea4ba73-a2c1-4821-9c9e-09369aacbabf', false, 'estadio', '-34.89233766031895,-56.15704536437988', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '2db79cf9-3811-4f3e-b1b4-23b86b3a8283', false, 'clinicas', '-34.890859230912774,-56.15189552307129', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '9efaad2f-8189-4a77-950d-9874a02f316f', false, 'Comercio', '-34.887092635773804,-56.12966537475586', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '73bc4092-999d-4cfd-a38f-b28e831bf819', false, 'H Yirigoyen', '-34.88762067353586,-56.11048221588135', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '0cc593b3-76b5-4161-89aa-0f7df9adcd77', false, 'Portones', '-34.883185050874395,-56.08245849609375', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'd4883ebe-c2a5-487a-8f77-39bee0e6d7dd', false, 'Puente delas America', '-34.87653116798392,-56.045165061950684', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '735ebdd4-7be6-4f8e-a6b2-ede7ee02e018', false, 'Aeropuerto', '-34.839731459420655,-56.019287109375', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '686a9123-08e1-4b34-9155-94e334720248', false, 'Lagomar', '-34.81817221720561,-55.97877502441406', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'c41069bd-8679-4d46-9fab-819ebd7e99ee', false, 'Bolivia', '-34.793294577935896,-55.940022468566895', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'fa27fd3e-9086-45dc-87cf-ceb97e6bf5e7', false, 'Peaje', '-34.786457197671105,-55.8900260925293', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '8f62914f-06ab-4cef-bfc8-5be22950b4dc', false, 'Neptunia', '-34.780817694621916,-55.872602462768555', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'd48ca6a1-338f-4fa0-b843-1e79b4a05ca5', false, 'Pinar', '-34.777715803604686,-55.85123062133789', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '6168805a-dfd0-4c74-8b7c-38b86cf21be6', false, 'Salinas', '-34.77560081104566,-55.839385986328125', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'e4bc0f09-0630-442d-9ee0-5c694af567f7', false, 'Marindia', '-34.772357717160965,-55.82050323486328', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '60ccf27c-bc3e-492e-89d0-ed4d041040bc', false, 'Fortin Sta Rosa', '-34.76953753191822,-55.805912017822266', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '4434c6c1-bc64-4793-846b-9b09f7134af1', false, 'Villa Argentina', '-34.76756334490676,-55.777587890625', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '348ba11b-7f7c-4620-80a8-92fd4d3a4249', false, 'Atlantida', '-34.766717250303486,-55.76291084289551', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '8c7eebec-04d3-41b2-99e0-6649177063be', false, 'Las Toscas', '-34.76079434523751,-55.73965072631836', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'a077ee38-9417-42c0-a0af-4625fb3edeeb', false, 'Parque Del Plata', '-34.75374271370495,-55.71836471557617', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '68ef97da-d3fd-4665-83c3-01ddf0028472', false, 'Las Vegas', '-34.7506398050501,-55.701026916503906', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '050be1ad-d311-4f0e-9e56-9bfdeab728ed', false, 'La Floresta', '-34.749793537001814,-55.6842041015625', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '4198d04a-f91b-4e75-a950-26b9a5de5018', false, 'Costa Azul', '-34.76051229153917,-55.66326141357422', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '68e955bb-2a3c-43db-a66b-4307d352acaf', false, 'Bello horizonte', '-34.76629419974942,-55.6431770324707', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '6e84cc8a-351c-4888-bc26-d879d63d7f80', false, 'Bello Horizonte', '-34.75289647745205,-55.642662048339844', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '94abf431-af99-4b54-9c94-f1dba1d8a9ee', false, 'Guazuvira', '-34.75501205182485,-55.61159133911133', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '05d4fda1-9328-4e10-bd5c-bed5a82fc0cc', false, 'San Luis', '-34.770806627203704,-55.57605743408203', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'dac04f49-438f-4f1c-aa9a-6c2d1d0e8917', false, 'La tuna', '-34.777574805787424,-55.55957794189453', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '7426ea21-12ed-4f7f-88b3-d6bc84f2bad0', false, 'Araminda', '-34.78152265358811,-55.55065155029297', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '8cfb908f-da6e-479d-9f1d-de81562d7173', false, 'Sta Lucia Del Este', '-34.78434242921046,-55.53606033325195', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '040865ec-b451-4881-9054-e54a0321602d', false, 'Biarritz', '-34.78800799341509,-55.5164909362793', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'ea1124d0-4d13-407e-b939-9f386c7c58da', false, 'Cuchilla Alta Ruta', '-34.7847653872402,-55.49812316894531', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '09efbfbd-cbb1-41d0-ab04-d45b8ca01583', false, 'Cuchilla alta', '-34.79392894530395,-55.49692153930664', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'd94274b3-c0b4-4368-a82f-bb2f96482a3f', false, 'Santa Ana', '-34.79096852249939,-55.46602249145508', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '3d26cfb4-e22a-42d8-8462-a6addf0f8ccc', false, 'Santa Ana', '-34.77954875330198,-55.46773910522461', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '4759fb25-cfe3-4b8e-a502-824ee900b243', false, 'Balneareo Argentino', '-34.78025372311158,-55.44130325317383', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '9e1d598f-7555-4eec-bb85-8a3e6ef0a6a7', false, 'Jaureguiberry Acapulco', '-34.780500461121754,-55.41804313659668', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '3240261f-1532-4ab3-b8af-f5b3b582220d', false, 'Jaureguiberry "el grillito"', '-34.78100274693366,-55.41203498840332', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '9276a300-26ae-4f30-ae7d-7718b2324d1f', false, 'Jaureguiberry', '-34.784518661986425,-55.39988994598389', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '61221ecb-61a0-4dcb-bd17-cb5fcf17f601', false, 'Peaje Solis', '-34.77856178544819,-55.39375305175781', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'ba3e7320-6903-496d-870f-1449ff18006d', false, 'Solis', '-34.78370798809935,-55.37959098815918', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'e84e028a-5072-4243-8875-1a8ad9d594dc', false, 'Las Flores', '-34.797876010378914,-55.38259506225586', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '46abb6e3-febc-41fa-b16f-5ee2e7d0b6e5', false, 'Bella Vista', '-34.80552291123269,-55.3551721572876', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '8baf5978-cb45-4e46-9f00-1bfdc353c705', false, 'Playa Verde', '-34.8222590430347,-55.314273834228516', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '171daf80-6982-48e1-9413-776a450bed9a', false, 'Playa Hermosa', '-34.83000936266889,-55.30963897705078', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '7f55527a-8d81-4e5f-a72f-3187844d05be', false, 'Playa Grande', '-34.8455078137077,-55.3022575378418', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '59507666-c77e-4a7e-9571-33134836bdcc', false, 'Piriapolis', '-34.8580453339167,-55.28989791870117', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '150c2dfd-4245-4ae6-a251-b9cc746cbeae', false, 'Piriapolis', '-34.864947198082824,-55.27050018310547', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '44236b41-3964-46e3-8b90-8615c07bed66', false, 'Cerro San Antonio', '-34.879734956615735,-55.260887145996094', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'a9c40613-cd2f-4535-bc51-12a31ad2ac03', false, 'Pta Colorada', '-34.90423452835512,-55.260372161865234', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '3ed90098-22c6-4d01-93f9-1f6481aff6a0', false, 'Pta Negra', '-34.89128164202929,-55.21162033081055', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'eca5e2e3-c540-415e-9911-620226f20875', false, 'Interbalnearea Americas Unidas', '-34.859735639915385,-55.16338348388672', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'db3a806d-e948-4629-82cd-b76be942244d', false, 'Pta Ballena', '-34.90564232769945,-55.037384033203125', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'ba72215b-54e1-41e1-b77b-061bb197730f', false, 'Pta del Este', '-34.939422270331754,-54.93610382080078', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '81fae9c4-da1e-4b8c-af65-1677896d98c9', false, 'Maldonado', '-34.904797650989074,-54.95532989501953', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '2c70adff-5705-4c1d-ae03-1eab75a12f00', false, 'Balneario Solis ruta 9', '-34.78293255344788,-55.345516204833984', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '3e0666c5-45f1-43d2-8a85-c2514a227448', false, 'Las Flores', '-34.7894177824241,-55.322513580322266', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'b5b88fca-be10-4155-8b1a-fa0de43043d4', false, 'Pta de ls Sierra', '-34.78688014485674,-55.27719497680664', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '016ec03e-d5a2-493e-ada1-06007aeb5b5e', false, 'Pan de Azucar', '-34.7801127296316,-55.2311897277832', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '63b84f4b-9b50-40d3-8eed-4e8c0203244d', false, 'La Paz', '-34.7568455057737,-56.25823974609375', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '14926e20-1da5-4bf2-9135-abf198535bc5', false, 'Santiago Vazquez', '-34.78673916270251,-56.355743408203125', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '82bc4ed2-043b-44e5-833d-20f07b740392', false, 'Colonia del Sacramento', '-34.452218472826544,-57.82928466796875', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', false, 'Paysandu', '-32.32195460020057,-58.08746337890625', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'c6253122-990a-4891-9456-6d5c68f0c2a9', false, 'Salto', '-31.475524020001792,-57.90069580078125', true);


 

INSERT INTO recorrido (id, eliminado, nombre) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', false, 'Montevideo-Jaureguiberri');


INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4ea4ba73-a2c1-4821-9c9e-09369aacbabf', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '2db79cf9-3811-4f3e-b1b4-23b86b3a8283', 2);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '9efaad2f-8189-4a77-950d-9874a02f316f', 3);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '73bc4092-999d-4cfd-a38f-b28e831bf819', 4);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '0cc593b3-76b5-4161-89aa-0f7df9adcd77', 5);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'd4883ebe-c2a5-487a-8f77-39bee0e6d7dd', 6);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '735ebdd4-7be6-4f8e-a6b2-ede7ee02e018', 7);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '686a9123-08e1-4b34-9155-94e334720248', 8);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'c41069bd-8679-4d46-9fab-819ebd7e99ee', 9);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'fa27fd3e-9086-45dc-87cf-ceb97e6bf5e7', 10);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '8f62914f-06ab-4cef-bfc8-5be22950b4dc', 11);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'd48ca6a1-338f-4fa0-b843-1e79b4a05ca5', 12);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '6168805a-dfd0-4c74-8b7c-38b86cf21be6', 13);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'e4bc0f09-0630-442d-9ee0-5c694af567f7', 14);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '60ccf27c-bc3e-492e-89d0-ed4d041040bc', 15);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4434c6c1-bc64-4793-846b-9b09f7134af1', 16);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '348ba11b-7f7c-4620-80a8-92fd4d3a4249', 17);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '8c7eebec-04d3-41b2-99e0-6649177063be', 18);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'a077ee38-9417-42c0-a0af-4625fb3edeeb', 19);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '68ef97da-d3fd-4665-83c3-01ddf0028472', 20);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '050be1ad-d311-4f0e-9e56-9bfdeab728ed', 21);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4198d04a-f91b-4e75-a950-26b9a5de5018', 22);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '68e955bb-2a3c-43db-a66b-4307d352acaf', 23);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '6e84cc8a-351c-4888-bc26-d879d63d7f80', 24);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '94abf431-af99-4b54-9c94-f1dba1d8a9ee', 25);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '05d4fda1-9328-4e10-bd5c-bed5a82fc0cc', 26);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'dac04f49-438f-4f1c-aa9a-6c2d1d0e8917', 27);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '7426ea21-12ed-4f7f-88b3-d6bc84f2bad0', 28);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '8cfb908f-da6e-479d-9f1d-de81562d7173', 29);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '040865ec-b451-4881-9054-e54a0321602d', 30);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '09efbfbd-cbb1-41d0-ab04-d45b8ca01583', 31);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'ea1124d0-4d13-407e-b939-9f386c7c58da', 32);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'd94274b3-c0b4-4368-a82f-bb2f96482a3f', 33);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '3d26cfb4-e22a-42d8-8462-a6addf0f8ccc', 34);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4759fb25-cfe3-4b8e-a502-824ee900b243', 35);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '9e1d598f-7555-4eec-bb85-8a3e6ef0a6a7', 36);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '3240261f-1532-4ab3-b8af-f5b3b582220d', 37);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '9276a300-26ae-4f30-ae7d-7718b2324d1f', 38);
