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

INSERT INTO configuracionempresa VALUES ('874c9ae5-88dd-4970-bfdb-d351b95e4683', true, true, 'ldap.lacbus.com', '#ab5d5d', '#ffffff', '#d6cece', '#000000', '#6f8c77', '#2d6b3f', NULL, 30, NULL, 'Solfy SA', true, true, '7odpM9eOsXzmGI3usgMwnEGKvBtFBOIGXjp8Km04bokZYXQXhmYc2A==', 'pk_test_6tXd8wYI7epiTi0RZznAe5m7', NULL, '2016-11-23 00:00:00', 'Solfy.com', 'ldap://127.0.0.1:389', 30);



INSERT INTO configuracionempresa_emails VALUES ('874c9ae5-88dd-4970-bfdb-d351b95e4683', 'contacto', 'contacto@solfy.com', 0);



INSERT INTO configuracionempresa_telefonos VALUES ('874c9ae5-88dd-4970-bfdb-d351b95e4683', 'general', '22003283', 0);


















INSERT INTO estadosencomienda VALUES ('9a265943-ab81-4a19-a752-03b2db475fed', 'Recibida');
INSERT INTO estadosencomienda VALUES ('c1423557-e9fb-472a-92f6-023328107117', 'Enviada');
INSERT INTO estadosencomienda VALUES ('f8be0436-76f0-44b0-88d8-4051bb844b41', 'Perdida');
INSERT INTO estadosencomienda VALUES ('cc9bf2f4-e09a-4067-82ab-4bd4e800c7a3', 'En viaje');
INSERT INTO estadosencomienda VALUES ('f75ebf4c-43a5-4331-91e6-bb16e94a42eb', 'Regresada');
INSERT INTO estadosencomienda VALUES ('f7036bc6-a43e-4d14-ac9c-cb35f182235b', 'Entregada');



INSERT INTO horario VALUES ('26716c50-5b02-437b-805f-0bbc5066da51', '08:00');
INSERT INTO horario VALUES ('7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '09:00');
INSERT INTO horario VALUES ('74b17b29-f87b-4c6a-ab70-839dac3525eb', '10:00');
INSERT INTO horario VALUES ('aa350bea-9505-4006-8973-1d7731d4497c', '11:00');
INSERT INTO horario VALUES ('7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '12:00');
INSERT INTO horario VALUES ('17501dd1-1d43-4dd6-b7ea-85306c69cd60', '13:00');
INSERT INTO horario VALUES ('1005ca8c-bfb7-4f97-a49e-15c07fefed71', '14:00');
INSERT INTO horario VALUES ('a47e07c6-d366-4cd0-b767-11d45e6acc59', '15:00');
INSERT INTO horario VALUES ('9ba095d6-c904-4fc6-9a8e-641f62605084', '16:00');
INSERT INTO horario VALUES ('79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '17:00');
INSERT INTO horario VALUES ('663cc264-6b7e-4c80-ab3c-fcba301b70a8', '18:00');
INSERT INTO horario VALUES ('d0e28b6a-1920-4d91-9da5-ca57441a36ea', '19:00');
INSERT INTO horario VALUES ('a7ae0f1f-2640-4b6f-a335-35d62cec1277', '10:00');
INSERT INTO horario VALUES ('d158531b-9155-417b-b396-031cb576d8d0', '12:00');
INSERT INTO horario VALUES ('6bae679c-5ad9-4507-b753-48898d4f4861', '14:00');
INSERT INTO horario VALUES ('e758cb53-962b-4c13-a24b-10e7c35a6f94', '16:00');
INSERT INTO horario VALUES ('a70692f8-2018-4114-9b54-fc3a74e0e420', '18:00');
INSERT INTO horario VALUES ('392d176e-b632-4ddd-b5cc-9ba53a6c68e3', '20:00');
INSERT INTO horario VALUES ('636b0918-b35f-49c0-9b2f-eee47a4941bc', '22:00');

INSERT INTO grupohorario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'Dias de semana', 'diasSemana');
INSERT INTO grupohorario VALUES ('24fcc68f-add9-4b45-8d27-f0887c2b1e38', 'Fines de semana', 'diasSemana');



INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', '26716c50-5b02-437b-805f-0bbc5066da51', 0);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', 1);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', '74b17b29-f87b-4c6a-ab70-839dac3525eb', 2);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'aa350bea-9505-4006-8973-1d7731d4497c', 3);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', 4);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', 5);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', 6);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', 7);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', '9ba095d6-c904-4fc6-9a8e-641f62605084', 8);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', 9);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', 10);
INSERT INTO grupohorario_horario VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', 11);
INSERT INTO grupohorario_horario VALUES ('24fcc68f-add9-4b45-8d27-f0887c2b1e38', 'a7ae0f1f-2640-4b6f-a335-35d62cec1277', 0);
INSERT INTO grupohorario_horario VALUES ('24fcc68f-add9-4b45-8d27-f0887c2b1e38', 'd158531b-9155-417b-b396-031cb576d8d0', 1);
INSERT INTO grupohorario_horario VALUES ('24fcc68f-add9-4b45-8d27-f0887c2b1e38', '6bae679c-5ad9-4507-b753-48898d4f4861', 2);
INSERT INTO grupohorario_horario VALUES ('24fcc68f-add9-4b45-8d27-f0887c2b1e38', 'e758cb53-962b-4c13-a24b-10e7c35a6f94', 3);
INSERT INTO grupohorario_horario VALUES ('24fcc68f-add9-4b45-8d27-f0887c2b1e38', 'a70692f8-2018-4114-9b54-fc3a74e0e420', 4);
INSERT INTO grupohorario_horario VALUES ('24fcc68f-add9-4b45-8d27-f0887c2b1e38', '392d176e-b632-4ddd-b5cc-9ba53a6c68e3', 5);
INSERT INTO grupohorario_horario VALUES ('24fcc68f-add9-4b45-8d27-f0887c2b1e38', '636b0918-b35f-49c0-9b2f-eee47a4941bc', 6);
INSERT INTO dias VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'Lunes', 0);
INSERT INTO dias VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'Domingo', 1);
INSERT INTO dias VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'Martes', 2);
INSERT INTO dias VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'Miercoles', 3);
INSERT INTO dias VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'Jueves', 4);
INSERT INTO dias VALUES ('07799863-48c7-4262-9ba1-5c26bef95fab', 'Viernes', 5);
INSERT INTO dias VALUES ('24fcc68f-add9-4b45-8d27-f0887c2b1e38', 'Sabado', 0);














INSERT INTO perfil VALUES ('ecefa62b-185a-4e48-a019-f67521e2b9cd', true, true, true, true, true, 'Admin');
INSERT INTO perfil VALUES ('40b64aed-ae63-4908-914d-b011188c171a', false, true, true, false, false, 'Vendedor');
INSERT INTO perfil VALUES ('49b26bf8-1be4-4405-a0d7-0356f644440d', false, true, false, false, false, 'Encargado Encomiendas');
INSERT INTO perfil VALUES ('fb192132-7a5c-427a-9c55-bc4b339e1ffd', false, false, true, false, false, 'Guarda');
INSERT INTO perfil VALUES ('d4d86bd5-60e5-4b45-abd4-58ea63af3b84', false, false, false, true, true, 'Encargado Mantenimiento Vehiculos');
INSERT INTO perfil VALUES ('219bb286-a869-4482-b757-48c7c24201aa', true, true, true, true, true, 'Admin');







INSERT INTO persona VALUES ('Empleado', 'c726c21c-f2e4-4094-a9b6-bb5811cf2e0e', 'Admin', 'a4fd8e6fa9fbf9a6f2c99e7b70aa9ef2', false, NULL, 'admin', NULL, 'System', NULL, NULL, NULL, NULL, NULL, NULL, 'ecefa62b-185a-4e48-a019-f67521e2b9cd');
INSERT INTO persona VALUES ('Empleado', '85627926-a2f7-436c-a184-d08a00131054', NULL, '0b05fdfa933b8bc9cd3f7dbf33f571a6', true, NULL, 'levy.dario@gmail.com', NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, '219bb286-a869-4482-b757-48c7c24201aa');
INSERT INTO persona VALUES ('Empleado', '425f2f82-8eb6-4790-8d26-52d4e10ff39a', 'Perez', NULL, false, NULL, 'jperez@solfy.com', NULL, 'Juan', NULL, NULL, NULL, NULL, 'admin', NULL, 'ecefa62b-185a-4e48-a019-f67521e2b9cd');











INSERT INTO perfil_persona VALUES ('219bb286-a869-4482-b757-48c7c24201aa', '85627926-a2f7-436c-a184-d08a00131054', 0);

INSERT INTO puntorecorrido VALUES ('Terminal', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', false, 'tres cruces', '-34.8940096615171,-56.16642236709595', true);
INSERT INTO puntorecorrido VALUES ('Parada', '4ea4ba73-a2c1-4821-9c9e-09369aacbabf', false, 'estadio', '-34.89233766031895,-56.15704536437988', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '2db79cf9-3811-4f3e-b1b4-23b86b3a8283', false, 'clinicas', '-34.890859230912774,-56.15189552307129', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '9efaad2f-8189-4a77-950d-9874a02f316f', false, 'Comercio', '-34.887092635773804,-56.12966537475586', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '73bc4092-999d-4cfd-a38f-b28e831bf819', false, 'H Yirigoyen', '-34.88762067353586,-56.11048221588135', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '0cc593b3-76b5-4161-89aa-0f7df9adcd77', false, 'Portones', '-34.883185050874395,-56.08245849609375', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'd4883ebe-c2a5-487a-8f77-39bee0e6d7dd', false, 'Puente delas America', '-34.87653116798392,-56.045165061950684', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '735ebdd4-7be6-4f8e-a6b2-ede7ee02e018', false, 'Aeropuerto', '-34.839731459420655,-56.019287109375', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '686a9123-08e1-4b34-9155-94e334720248', false, 'Lagomar', '-34.81817221720561,-55.97877502441406', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'c41069bd-8679-4d46-9fab-819ebd7e99ee', false, 'Bolivia', '-34.793294577935896,-55.940022468566895', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'fa27fd3e-9086-45dc-87cf-ceb97e6bf5e7', false, 'Peaje', '-34.786457197671105,-55.8900260925293', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '8f62914f-06ab-4cef-bfc8-5be22950b4dc', false, 'Neptunia', '-34.780817694621916,-55.872602462768555', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'd48ca6a1-338f-4fa0-b843-1e79b4a05ca5', false, 'Pinar', '-34.777715803604686,-55.85123062133789', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '6168805a-dfd0-4c74-8b7c-38b86cf21be6', false, 'Salinas', '-34.77560081104566,-55.839385986328125', true);
INSERT INTO puntorecorrido VALUES ('Parada', 'e4bc0f09-0630-442d-9ee0-5c694af567f7', false, 'Marindia', '-34.772357717160965,-55.82050323486328', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '60ccf27c-bc3e-492e-89d0-ed4d041040bc', false, 'Fortin Sta Rosa', '-34.76953753191822,-55.805912017822266', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '4434c6c1-bc64-4793-846b-9b09f7134af1', false, 'Villa Argentina', '-34.76756334490676,-55.777587890625', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '348ba11b-7f7c-4620-80a8-92fd4d3a4249', false, 'Atlantida', '-34.766717250303486,-55.76291084289551', true);
INSERT INTO puntorecorrido VALUES ('Parada', '8c7eebec-04d3-41b2-99e0-6649177063be', false, 'Las Toscas', '-34.76079434523751,-55.73965072631836', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', 'a077ee38-9417-42c0-a0af-4625fb3edeeb', false, 'Parque Del Plata', '-34.75374271370495,-55.71836471557617', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '68ef97da-d3fd-4665-83c3-01ddf0028472', false, 'Las Vegas', '-34.7506398050501,-55.701026916503906', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '050be1ad-d311-4f0e-9e56-9bfdeab728ed', false, 'La Floresta', '-34.749793537001814,-55.6842041015625', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '4198d04a-f91b-4e75-a950-26b9a5de5018', false, 'Costa Azul', '-34.76051229153917,-55.66326141357422', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '68e955bb-2a3c-43db-a66b-4307d352acaf', false, 'Bello horizonte', '-34.76629419974942,-55.6431770324707', true);
INSERT INTO puntorecorrido VALUES ('Parada', '6e84cc8a-351c-4888-bc26-d879d63d7f80', false, 'Bello Horizonte', '-34.75289647745205,-55.642662048339844', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '94abf431-af99-4b54-9c94-f1dba1d8a9ee', false, 'Guazuvira', '-34.75501205182485,-55.61159133911133', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '05d4fda1-9328-4e10-bd5c-bed5a82fc0cc', false, 'San Luis', '-34.770806627203704,-55.57605743408203', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'dac04f49-438f-4f1c-aa9a-6c2d1d0e8917', false, 'La tuna', '-34.777574805787424,-55.55957794189453', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '7426ea21-12ed-4f7f-88b3-d6bc84f2bad0', false, 'Araminda', '-34.78152265358811,-55.55065155029297', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '8cfb908f-da6e-479d-9f1d-de81562d7173', false, 'Sta Lucia Del Este', '-34.78434242921046,-55.53606033325195', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '040865ec-b451-4881-9054-e54a0321602d', false, 'Biarritz', '-34.78800799341509,-55.5164909362793', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'ea1124d0-4d13-407e-b939-9f386c7c58da', false, 'Cuchilla Alta Ruta', '-34.7847653872402,-55.49812316894531', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '09efbfbd-cbb1-41d0-ab04-d45b8ca01583', false, 'Cuchilla alta', '-34.79392894530395,-55.49692153930664', true);
INSERT INTO puntorecorrido VALUES ('Parada', 'd94274b3-c0b4-4368-a82f-bb2f96482a3f', false, 'Santa Ana', '-34.79096852249939,-55.46602249145508', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '3d26cfb4-e22a-42d8-8462-a6addf0f8ccc', false, 'Santa Ana', '-34.77954875330198,-55.46773910522461', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '4759fb25-cfe3-4b8e-a502-824ee900b243', false, 'Balneareo Argentino', '-34.78025372311158,-55.44130325317383', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '9e1d598f-7555-4eec-bb85-8a3e6ef0a6a7', false, 'Jaureguiberry Acapulco', '-34.780500461121754,-55.41804313659668', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '3240261f-1532-4ab3-b8af-f5b3b582220d', false, 'Jaureguiberry "el grillito"', '-34.78100274693366,-55.41203498840332', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '9276a300-26ae-4f30-ae7d-7718b2324d1f', false, 'Jaureguiberry', '-34.784518661986425,-55.39988994598389', true);
INSERT INTO puntorecorrido VALUES ('Parada', '61221ecb-61a0-4dcb-bd17-cb5fcf17f601', false, 'Peaje Solis', '-34.77856178544819,-55.39375305175781', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'ba3e7320-6903-496d-870f-1449ff18006d', false, 'Solis', '-34.78370798809935,-55.37959098815918', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'e84e028a-5072-4243-8875-1a8ad9d594dc', false, 'Las Flores', '-34.797876010378914,-55.38259506225586', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '46abb6e3-febc-41fa-b16f-5ee2e7d0b6e5', false, 'Bella Vista', '-34.80552291123269,-55.3551721572876', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '8baf5978-cb45-4e46-9f00-1bfdc353c705', false, 'Playa Verde', '-34.8222590430347,-55.314273834228516', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '171daf80-6982-48e1-9413-776a450bed9a', false, 'Playa Hermosa', '-34.83000936266889,-55.30963897705078', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '7f55527a-8d81-4e5f-a72f-3187844d05be', false, 'Playa Grande', '-34.8455078137077,-55.3022575378418', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '59507666-c77e-4a7e-9571-33134836bdcc', false, 'Piriapolis', '-34.8580453339167,-55.28989791870117', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '150c2dfd-4245-4ae6-a251-b9cc746cbeae', false, 'Piriapolis', '-34.864947198082824,-55.27050018310547', true);
INSERT INTO puntorecorrido VALUES ('Parada', '44236b41-3964-46e3-8b90-8615c07bed66', false, 'Cerro San Antonio', '-34.879734956615735,-55.260887145996094', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'a9c40613-cd2f-4535-bc51-12a31ad2ac03', false, 'Pta Colorada', '-34.90423452835512,-55.260372161865234', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '3ed90098-22c6-4d01-93f9-1f6481aff6a0', false, 'Pta Negra', '-34.89128164202929,-55.21162033081055', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'eca5e2e3-c540-415e-9911-620226f20875', false, 'Interbalnearea Americas Unidas', '-34.859735639915385,-55.16338348388672', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'db3a806d-e948-4629-82cd-b76be942244d', false, 'Pta Ballena', '-34.90564232769945,-55.037384033203125', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', 'ba72215b-54e1-41e1-b77b-061bb197730f', false, 'Pta del Este', '-34.939422270331754,-54.93610382080078', true);
INSERT INTO puntorecorrido VALUES ('Terminal', '81fae9c4-da1e-4b8c-af65-1677896d98c9', false, 'Maldonado', '-34.904797650989074,-54.95532989501953', true);
INSERT INTO puntorecorrido VALUES ('Parada', '2c70adff-5705-4c1d-ae03-1eab75a12f00', false, 'Balneario Solis ruta 9', '-34.78293255344788,-55.345516204833984', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '3e0666c5-45f1-43d2-8a85-c2514a227448', false, 'Las Flores', '-34.7894177824241,-55.322513580322266', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'b5b88fca-be10-4155-8b1a-fa0de43043d4', false, 'Pta de ls Sierra', '-34.78688014485674,-55.27719497680664', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '016ec03e-d5a2-493e-ada1-06007aeb5b5e', false, 'Pan de Azucar', '-34.7801127296316,-55.2311897277832', true);
INSERT INTO puntorecorrido VALUES ('Parada', '63b84f4b-9b50-40d3-8eed-4e8c0203244d', false, 'La Paz', '-34.7568455057737,-56.25823974609375', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '14926e20-1da5-4bf2-9135-abf198535bc5', false, 'Santiago Vazquez', '-34.78673916270251,-56.355743408203125', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '82bc4ed2-043b-44e5-833d-20f07b740392', false, 'Colonia del Sacramento', '-34.452218472826544,-57.82928466796875', true);
INSERT INTO puntorecorrido VALUES ('Terminal', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', false, 'Paysandu', '-32.32195460020057,-58.08746337890625', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', 'c6253122-990a-4891-9456-6d5c68f0c2a9', false, 'Salto', '-31.475524020001792,-57.90069580078125', true);
INSERT INTO puntorecorrido VALUES ('Parada', '73eb1904-ca19-411d-bc9d-7721f918788d', false, 'Br Artigas y Nueva Palmira', '-34.88797269682541,-56.16562843322754', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '38362a16-649c-4690-88af-ae5f95f19b95', false, 'Br Artigas y Gral Flores', '-34.8702992654236,-56.174211502075195', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '2a4b6025-9bb7-4043-ae91-e5ba8ea5cd09', false, 'Br Artigas y Millan', '-34.87139076270599,-56.190176010131836', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '137e9f91-ca93-444c-b3f9-0348040ac236', false, 'Plaza Cuba', '-34.87228859638293,-56.202192306518555', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '46cf22d5-2d17-461f-8e34-e346c7edfb5e', false, 'Ruta 1 y Carlos Maria Ramirez', '-34.87079220147443,-56.24176025390625', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '9f6a3266-1c4b-4429-b755-40c7a969662e', false, 'Ruta 5 y Luis Batlle Berres', '-34.84395809990988,-56.25394821166992', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', 'f830bde1-533b-4917-8b63-42eca3de5d7f', false, 'Ruta 5 y Ruta 102', '-34.84395809990988,-56.25394821166992', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '9c177a5c-6268-4f0e-a256-2b99216ef6ba', false, 'Ruta 5 y Ruta 102', '-34.774190785887605,-56.270599365234375', NULL);
INSERT INTO puntorecorrido VALUES ('Parada', '144e465b-ee00-45ca-aa01-b7b376166e1a', false, 'Ruta 5 y Ruta 108', '-34.721156359000666,-56.24570846557617', NULL);
INSERT INTO puntorecorrido VALUES ('Terminal', '26737d91-16c9-4d99-ba35-0fd44dece1c0', false, 'Terminal Las Piedras', '-34.72754062885772,-56.22021675109863', NULL);

INSERT INTO precio VALUES ('333ec2fc-8737-4e8d-ba68-f47b3c11adb4', 120, '26737d91-16c9-4d99-ba35-0fd44dece1c0', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7');
INSERT INTO precio VALUES ('4533af82-1248-40c5-a6d5-4e7211f39fc5', 80, '137e9f91-ca93-444c-b3f9-0348040ac236', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7');
INSERT INTO precio VALUES ('6c6be21b-4253-4942-906a-36c1dff5b19f', 70, '26737d91-16c9-4d99-ba35-0fd44dece1c0', '137e9f91-ca93-444c-b3f9-0348040ac236');

INSERT INTO recorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', false, 'Montevideo-Jaureguiberri');
INSERT INTO recorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', false, 'Tres Cruces - Las Piedras');




INSERT INTO recorrido_grupohorario VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '07799863-48c7-4262-9ba1-5c26bef95fab', 0);
INSERT INTO recorrido_grupohorario VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '24fcc68f-add9-4b45-8d27-f0887c2b1e38', 1);



INSERT INTO recorrido_precio VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '333ec2fc-8737-4e8d-ba68-f47b3c11adb4', 0);
INSERT INTO recorrido_precio VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '4533af82-1248-40c5-a6d5-4e7211f39fc5', 1);
INSERT INTO recorrido_precio VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '6c6be21b-4253-4942-906a-36c1dff5b19f', 2);



INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4ea4ba73-a2c1-4821-9c9e-09369aacbabf', 1);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '2db79cf9-3811-4f3e-b1b4-23b86b3a8283', 2);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '9efaad2f-8189-4a77-950d-9874a02f316f', 3);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '73bc4092-999d-4cfd-a38f-b28e831bf819', 4);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '0cc593b3-76b5-4161-89aa-0f7df9adcd77', 5);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'd4883ebe-c2a5-487a-8f77-39bee0e6d7dd', 6);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '735ebdd4-7be6-4f8e-a6b2-ede7ee02e018', 7);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '686a9123-08e1-4b34-9155-94e334720248', 8);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'c41069bd-8679-4d46-9fab-819ebd7e99ee', 9);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'fa27fd3e-9086-45dc-87cf-ceb97e6bf5e7', 10);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '8f62914f-06ab-4cef-bfc8-5be22950b4dc', 11);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'd48ca6a1-338f-4fa0-b843-1e79b4a05ca5', 12);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '6168805a-dfd0-4c74-8b7c-38b86cf21be6', 13);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'e4bc0f09-0630-442d-9ee0-5c694af567f7', 14);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '60ccf27c-bc3e-492e-89d0-ed4d041040bc', 15);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4434c6c1-bc64-4793-846b-9b09f7134af1', 16);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '348ba11b-7f7c-4620-80a8-92fd4d3a4249', 17);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '8c7eebec-04d3-41b2-99e0-6649177063be', 18);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'a077ee38-9417-42c0-a0af-4625fb3edeeb', 19);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '68ef97da-d3fd-4665-83c3-01ddf0028472', 20);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '050be1ad-d311-4f0e-9e56-9bfdeab728ed', 21);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4198d04a-f91b-4e75-a950-26b9a5de5018', 22);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '68e955bb-2a3c-43db-a66b-4307d352acaf', 23);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '6e84cc8a-351c-4888-bc26-d879d63d7f80', 24);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '94abf431-af99-4b54-9c94-f1dba1d8a9ee', 25);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '05d4fda1-9328-4e10-bd5c-bed5a82fc0cc', 26);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'dac04f49-438f-4f1c-aa9a-6c2d1d0e8917', 27);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '7426ea21-12ed-4f7f-88b3-d6bc84f2bad0', 28);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '8cfb908f-da6e-479d-9f1d-de81562d7173', 29);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '040865ec-b451-4881-9054-e54a0321602d', 30);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '09efbfbd-cbb1-41d0-ab04-d45b8ca01583', 31);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'ea1124d0-4d13-407e-b939-9f386c7c58da', 32);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'd94274b3-c0b4-4368-a82f-bb2f96482a3f', 33);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '3d26cfb4-e22a-42d8-8462-a6addf0f8ccc', 34);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4759fb25-cfe3-4b8e-a502-824ee900b243', 35);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '9e1d598f-7555-4eec-bb85-8a3e6ef0a6a7', 36);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '3240261f-1532-4ab3-b8af-f5b3b582220d', 37);
INSERT INTO recorrido_puntorecorrido VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '9276a300-26ae-4f30-ae7d-7718b2324d1f', 38);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '73eb1904-ca19-411d-bc9d-7721f918788d', 1);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '38362a16-649c-4690-88af-ae5f95f19b95', 2);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '2a4b6025-9bb7-4043-ae91-e5ba8ea5cd09', 3);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '137e9f91-ca93-444c-b3f9-0348040ac236', 4);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '46cf22d5-2d17-461f-8e34-e346c7edfb5e', 5);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '9f6a3266-1c4b-4429-b755-40c7a969662e', 6);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '9c177a5c-6268-4f0e-a256-2b99216ef6ba', 7);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '63b84f4b-9b50-40d3-8eed-4e8c0203244d', 8);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '144e465b-ee00-45ca-aa01-b7b376166e1a', 9);
INSERT INTO recorrido_puntorecorrido VALUES ('9e3440cf-b6b5-44b5-9427-a2215f792336', '26737d91-16c9-4d99-ba35-0fd44dece1c0', 10);



INSERT INTO reglacobroencomienda VALUES ('42f504dc-094f-4953-a7b0-426e618e2414', 'Por peso', true);
INSERT INTO reglacobroencomienda VALUES ('6363e537-b438-4bdb-a618-e38194d76010', 'Por unidad', false);


INSERT INTO reglacobroencomiendacriteria VALUES ('e13b53e5-6ab8-4674-a1c0-8d766ae1bb20', '<=', 100, 1);
INSERT INTO reglacobroencomiendacriteria VALUES ('b3d75fdc-7807-42b0-aeef-eb7367eebece', '<=', 120, 2);
INSERT INTO reglacobroencomiendacriteria VALUES ('2fc6df6f-2b24-4519-8456-77fe6f95f9d6', '<=', 130, 3);
INSERT INTO reglacobroencomiendacriteria VALUES ('2895c802-7a12-44f0-842a-081c2d8ec885', '>', 140, 3);
INSERT INTO reglacobroencomiendacriteria VALUES ('3ac73d8c-da1b-4593-ac47-8a7f15880479', '<=', 100, 1);
INSERT INTO reglacobroencomiendacriteria VALUES ('a3ef0f92-f55c-49d1-8c2a-2b388c1500a5', '<=', 90, 2);
INSERT INTO reglacobroencomiendacriteria VALUES ('1327576c-d695-41a2-b581-d52d0883117e', '<=', 80, 3);
INSERT INTO reglacobroencomiendacriteria VALUES ('fd0acc21-02a8-4a24-bb00-6a25f2abf2a2', '<=', 70, 4);
INSERT INTO reglacobroencomiendacriteria VALUES ('110d92d3-544c-41ee-90a1-90bf51db56ae', '>', 50, 4);



INSERT INTO reglacobroencomienda_reglacobroencomiendacriteria VALUES ('42f504dc-094f-4953-a7b0-426e618e2414', 'e13b53e5-6ab8-4674-a1c0-8d766ae1bb20', 0);
INSERT INTO reglacobroencomienda_reglacobroencomiendacriteria VALUES ('42f504dc-094f-4953-a7b0-426e618e2414', 'b3d75fdc-7807-42b0-aeef-eb7367eebece', 1);
INSERT INTO reglacobroencomienda_reglacobroencomiendacriteria VALUES ('42f504dc-094f-4953-a7b0-426e618e2414', '2fc6df6f-2b24-4519-8456-77fe6f95f9d6', 2);
INSERT INTO reglacobroencomienda_reglacobroencomiendacriteria VALUES ('42f504dc-094f-4953-a7b0-426e618e2414', '2895c802-7a12-44f0-842a-081c2d8ec885', 3);
INSERT INTO reglacobroencomienda_reglacobroencomiendacriteria VALUES ('6363e537-b438-4bdb-a618-e38194d76010', '3ac73d8c-da1b-4593-ac47-8a7f15880479', 0);
INSERT INTO reglacobroencomienda_reglacobroencomiendacriteria VALUES ('6363e537-b438-4bdb-a618-e38194d76010', 'a3ef0f92-f55c-49d1-8c2a-2b388c1500a5', 1);
INSERT INTO reglacobroencomienda_reglacobroencomiendacriteria VALUES ('6363e537-b438-4bdb-a618-e38194d76010', '1327576c-d695-41a2-b581-d52d0883117e', 2);
INSERT INTO reglacobroencomienda_reglacobroencomiendacriteria VALUES ('6363e537-b438-4bdb-a618-e38194d76010', 'fd0acc21-02a8-4a24-bb00-6a25f2abf2a2', 3);
INSERT INTO reglacobroencomienda_reglacobroencomiendacriteria VALUES ('6363e537-b438-4bdb-a618-e38194d76010', '110d92d3-544c-41ee-90a1-90bf51db56ae', 4);


INSERT INTO encomienda VALUES ('7e4367ef-31a7-45ed-98de-d58ba2c76dca', 1, '1837287-2', '2938049-2', 'Uruguay 252', false, NULL, '2016-11-23 16:36:49.033', 0.800000012, NULL, true, 100, true, NULL, '092947283', NULL, '099283672', NULL, '26737d91-16c9-4d99-ba35-0fd44dece1c0', NULL, NULL, 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', NULL, '42f504dc-094f-4953-a7b0-426e618e2414', NULL);
INSERT INTO encomienda VALUES ('70edb8ca-1d43-4111-a124-14d31bb33c17', 2, '2983949-2', '1387238-9', 'Rivera 937', false, NULL, '2016-11-23 16:37:52.824', 2, NULL, true, 180, NULL, NULL, '091837192', NULL, '099217283', NULL, '26737d91-16c9-4d99-ba35-0fd44dece1c0', NULL, NULL, 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', NULL, '6363e537-b438-4bdb-a618-e38194d76010', NULL);









INSERT INTO vehiculo VALUES ('2feb31d1-f809-40e5-af5b-4e7aa5f72f8d', 2010, 44, NULL, false, false, NULL, 'Volvo', 'STP1839', 'B58E', '100');
INSERT INTO vehiculo VALUES ('bfdcd8ce-07b9-41a2-9552-6191a4c33471', 2014, 40, NULL, false, false, NULL, 'Mercedes', 'STP2232', 'T23', '102');
INSERT INTO vehiculo VALUES ('36bff139-0f31-4f75-bfd3-7ea6d08b6aa0', 2014, 40, NULL, false, false, NULL, 'Mercedes', 'STP2342', 'T30', '130');









INSERT INTO viaje VALUES ('e1e2e0fb-7912-4531-a014-cf2ac8311989', '2016-11-23 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ba07a4dc-9e56-4b9c-80cd-feb83d61c63d', '2016-11-23 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('5a1500ba-fa9d-4bbd-8dee-fd5d1f3a3580', '2016-11-23 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4d92b9ff-2989-4d7b-bcdd-a0c268afdfd5', '2016-11-23 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('27179842-059d-4085-b0d4-27533b4a3d3a', '2016-11-23 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a68f5cc4-c7df-4cf4-8277-11834c7320d4', '2016-11-23 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('9ae3d513-17d7-449f-b061-5a76c739272b', '2016-11-23 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('7c3a37d3-2e63-48c7-b912-c46aeae68c02', '2016-11-23 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('5b11261b-d03c-4d0c-b302-ffccab4f5c1b', '2016-11-23 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('77db5bee-9e9f-464d-a9e3-a48d633f50b8', '2016-11-23 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8a2c47b7-3ca3-4453-b371-47902c605da2', '2016-11-23 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('faa3ecbb-07e2-422f-8c39-d053cd11e380', '2016-11-23 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('37a3f623-f849-41b3-81dd-9713ed4d30a8', '2016-11-24 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3f30ab1b-f066-49fb-aa9b-d8c8fd195b0b', '2016-11-24 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6ec5ac40-7dc8-4238-8b65-912fba15d8ce', '2016-11-24 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('9535938a-fff3-43b3-9677-509e795959d1', '2016-11-24 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('53999af7-60bf-44ef-82be-9505688dea7d', '2016-11-24 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('abc43408-d0a0-4327-9d09-521110e15963', '2016-11-24 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3ccf2a52-eb1c-4ecc-af5d-86e92938342c', '2016-11-24 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cb24afac-6c0b-47a6-9639-b412e1d592ec', '2016-11-24 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('2218d299-373d-4194-89c5-738732fff657', '2016-11-24 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cecda037-87de-4473-a2aa-461868522c84', '2016-11-24 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('194bc9de-e49d-4b6d-8b20-6d866842b848', '2016-11-24 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c17a88df-92fe-4748-82ce-5d462b279e1b', '2016-11-24 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8dd6bc0b-f285-49d6-a48a-93292d09113d', '2016-11-25 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ce6c1577-6482-4950-a96d-651eb1167d43', '2016-11-25 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('b70ba1c2-7e71-4cd8-826c-abbe96310573', '2016-11-25 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('43996578-eecf-41ee-b340-a02885532fe2', '2016-11-25 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('017e7438-9038-4cc8-aa50-cd8ab8debac5', '2016-11-25 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c55a6cc7-e82e-490a-920b-29cd2b78a2d7', '2016-11-25 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c6e4153f-c046-4687-8428-7abee68c025c', '2016-11-25 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('327d30cd-e851-4738-b9e4-67ecf2f2bf75', '2016-11-25 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ca347963-86cc-416f-bf81-9fa83f7767d7', '2016-11-25 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('95ad08ac-fdf6-4bb2-b59d-1ef04cbcd94b', '2016-11-25 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6ba91789-3719-40d5-a0db-133dd0f74a13', '2016-11-25 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('58ca6f9f-3ee4-4907-8c71-1071ea5f7948', '2016-11-25 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('67576561-2ff6-45de-8ac8-0468378e8eaa', '2016-11-27 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('34842643-3b57-4269-9662-a59c34f1087c', '2016-11-27 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4e802ab3-7cce-42a2-a205-7b976786876f', '2016-11-27 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('2a9d240b-8db5-46b1-8725-aa0d3a877b7c', '2016-11-27 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('20fd6c4a-75bd-47c2-8e7d-4a9c1d23cea0', '2016-11-27 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('07cc7447-9e38-4d24-9341-669195bc8e68', '2016-11-27 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3d16d8c6-b369-4d34-965f-2d1e153fecd9', '2016-11-27 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4981f322-8bc3-4ca7-92a1-8fb179f3037b', '2016-11-27 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f6254ce6-2002-4790-a223-2011616c9328', '2016-11-27 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6190c6df-13e6-492d-99bf-7f13b3fda028', '2016-11-27 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('1de9e696-8f08-4699-9d9e-cb6c46744a53', '2016-11-27 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('21abe883-6bb4-470b-a889-e4414a2dc186', '2016-11-27 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('d418ac4b-19ca-468c-a058-092dd4020b84', '2016-11-28 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('284480ca-087f-47b3-b09e-a4225a8f2a1e', '2016-11-28 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('517ddc77-55e2-4f55-96f2-a286ba49404b', '2016-11-28 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('7008b051-7311-43ba-a7df-7466cac829d7', '2016-11-28 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('62924179-c12a-4a1e-bd17-fc82aead6be1', '2016-11-28 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('d98a9be3-8b98-42b5-b0bc-8b001397f19e', '2016-11-28 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a2b368fb-470d-4ca7-ba2a-7b5ee543895d', '2016-11-28 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8852ee2b-407c-4496-bdf8-59695691f475', '2016-11-28 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('73e8387b-8343-419c-a00f-8ee580ddbaad', '2016-11-28 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a629cb8d-8c1c-442e-a4ef-bff70b588fa8', '2016-11-28 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('812ae302-b068-4215-bde7-5160d6224514', '2016-11-28 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8efd189e-b79e-4349-82c2-2f6bc3999fe8', '2016-11-28 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('51003b59-daff-4d10-95f1-316b14dec6be', '2016-11-29 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c6cc58b2-b6aa-4464-a729-4d76f58ada48', '2016-11-29 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('e55ad925-98f9-4482-a742-5dfb9c360159', '2016-11-29 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('33e75a7d-d952-4805-b4ab-a498b7c0fe20', '2016-11-29 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3338f9a2-b050-47d2-a088-84d46823d38e', '2016-11-29 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c2fdc00d-ba20-4d7e-9e28-1f8c1e488916', '2016-11-29 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a4bd67b3-c7fa-460d-bad6-140e93bcc7f5', '2016-11-29 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f4fd1aea-ba9a-4b0c-9a0e-7b18d9958049', '2016-11-29 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('0a7aea74-1089-40f7-b8ef-94447d42ce15', '2016-11-29 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('87189702-3736-4853-b239-71c5a5e428a8', '2016-11-29 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a2d1e824-3246-43df-a300-878fddb12303', '2016-11-29 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4057404d-ec12-41b6-8466-f3513c6b2cda', '2016-11-29 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('964376df-2deb-40cb-8419-7e4685091015', '2016-11-30 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4b849a69-6005-4aa5-b511-2b2c34731d0d', '2016-11-30 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('361f3d3e-ccea-4b00-b60b-83cd700a330d', '2016-11-30 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('093e25e2-ddcc-488b-aa19-dddb11770060', '2016-11-30 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('00aef63b-e0d1-47fb-bf8f-7f9cf0c7552d', '2016-11-30 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('12e471bb-614f-487e-844d-801f743173b3', '2016-11-30 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c8d2abb4-a5ed-4969-b57a-17bcb7641b33', '2016-11-30 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ba035ac9-88f1-444e-a2f6-56c7a0c54189', '2016-11-30 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('958d21ce-4807-45ca-bb39-c7590dbd3ec8', '2016-11-30 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('97ed0ea2-9be7-4671-9df0-5cbcabecc421', '2016-11-30 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ea984f2a-fcdf-49b7-94e0-0563b1f1398a', '2016-11-30 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('24296855-9aa7-46c7-92ec-c28825d8e6f6', '2016-11-30 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('b10f49dd-c990-4a9e-a296-8f041ac4f4db', '2016-12-01 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('0f3b6ac1-b459-42e4-93e1-59b09494f294', '2016-12-01 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8138ee88-f046-4b49-aec2-7c08e024a557', '2016-12-01 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('2a907f77-5646-49fd-b442-e975ed06d527', '2016-12-01 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ace09f24-ea3e-4cc5-8179-2b47e6b6520b', '2016-12-01 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('7e384e10-1ba1-4f90-8671-e19b3b1da794', '2016-12-01 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ba60839b-01ff-48b8-a3a9-a1d705b1c79b', '2016-12-01 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('7ab697c9-79e7-4641-a05e-208a9605133c', '2016-12-01 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6396d573-6db4-4052-8fd5-cee45a0d29f4', '2016-12-01 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('dfcf1c96-9113-4041-817d-90f733895980', '2016-12-01 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4a4f7d78-41cb-4403-a95f-c02346125032', '2016-12-01 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4fe86021-9cec-475f-90a2-e3f398c0c059', '2016-12-01 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('64aa2ef4-7b12-410a-b2a0-8d2bcba8061b', '2016-12-02 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('132cba8a-3cc2-4970-9335-c1fcea9ed6a5', '2016-12-02 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('2bf12f96-a24f-4a56-9fe7-0851487a5a03', '2016-12-02 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('5258404e-c23b-48d9-b127-e5e26058291f', '2016-12-02 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('38cfb87b-02a5-46ac-8855-74dbd2bcbe8a', '2016-12-02 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('40b3b5cf-fa4d-4c1f-828e-ae4dee8aad54', '2016-12-02 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('d2a03548-7574-49a6-ad96-ee078b7720cb', '2016-12-02 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6f798dcf-6059-4841-9138-7ab729f41f8b', '2016-12-02 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('e69a0ea1-e427-4b8c-9d4a-c679f6189240', '2016-12-02 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('bb9b964f-ab36-48c2-be97-0ca4928e4e05', '2016-12-02 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3b653d2d-9e6e-4e45-af91-154b21d3b8b0', '2016-12-02 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('bd7f118f-b26f-4b11-a514-3b11769980ad', '2016-12-02 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('32ff88f5-8edb-48fe-9ab4-5d3a8f531f4c', '2016-12-04 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8924fdd3-4fb5-4da5-b8b5-8a5015e08925', '2016-12-04 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('22a9d969-31eb-46e2-a602-d8c11fe70e85', '2016-12-04 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('be828324-b456-4fb2-84a8-d4fef557274a', '2016-12-04 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a32339bf-d31d-4213-b810-d4c486497744', '2016-12-04 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f691aeab-aaf1-4d38-b685-fac41907e31b', '2016-12-04 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('77664fd1-0193-49bc-8359-92cedd2a88fc', '2016-12-04 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('56e59fbd-683d-4f4c-a770-8f6df3303454', '2016-12-04 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6c98b0b7-37f2-4a42-b525-1d3d6255f8a7', '2016-12-04 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('b14df716-e8bd-488a-bf09-00c263dfd786', '2016-12-04 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8a910bf6-ee0b-45be-a940-83bbccf96daa', '2016-12-04 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('2d245d82-ca21-4ca3-b831-e687d985f851', '2016-12-04 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('18e91b01-0d39-4dce-9945-56dcee8c8659', '2016-12-05 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('11afa6a0-2dc0-4841-b6a0-6e59b8bf5005', '2016-12-05 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('945bb7eb-9a7f-4041-8c65-01206724188e', '2016-12-05 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cca89ec2-e2fd-4644-a0b2-05b635aa7a11', '2016-12-05 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('868e481d-8d2f-488a-919a-a4f378f70abb', '2016-12-05 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('02945acd-c7be-43e6-a0f0-a5a56faac81a', '2016-12-05 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('460b4350-a9e5-4cc6-ade7-959d463d3a2e', '2016-12-05 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ab4d933a-20d0-47fe-ba68-2e746aa602bb', '2016-12-05 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6be17869-0ca9-48ed-bc2b-80116065d889', '2016-12-05 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c9b97d53-99db-424b-b706-a0a0fde28a79', '2016-12-05 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('611462c5-9d7d-484a-8453-2a48b4c4e429', '2016-12-05 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8592702b-f140-4bff-b2c0-3e1e9ba233d7', '2016-12-05 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('19d4857e-7d01-4b7e-8175-ad7ad1ff9260', '2016-12-06 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cc6cdeb7-46c4-4957-bf9f-3a0a77f84057', '2016-12-06 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('def4c927-9340-4853-94ad-30b101eaf15a', '2016-12-06 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('78b0e198-c482-4a9b-b6d9-928d642eab66', '2016-12-06 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('30bf7e82-4d3c-4887-935a-2d718ad6066e', '2016-12-06 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('d97d39e9-554f-4e82-8176-43c1495d4498', '2016-12-06 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('146c7a68-9dc1-41f8-857e-73676048425d', '2016-12-06 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c0a8e5fb-e5e7-4edc-8a25-58e848cdc9d3', '2016-12-06 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('9f714404-3dd7-4d25-9200-4bf861aa698e', '2016-12-06 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('418ed985-5468-43b0-9ceb-5fb7578fb890', '2016-12-06 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3c88ef8a-aa13-4cba-8d3c-00fe0d5019d8', '2016-12-06 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('5d8cc9bd-6540-4469-9efb-2ff3a04d161d', '2016-12-06 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('deefe6b1-5748-4cf3-886b-b3f362e84ace', '2016-12-07 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f2ddc6b3-943d-415d-a53c-d08bebf9c7fe', '2016-12-07 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('514712bd-fa70-4f13-9096-d328f69eca52', '2016-12-07 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a69ac481-4f4b-4d42-b393-80d8a7cbd29e', '2016-12-07 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('b3456035-a0da-41b8-b232-e7b471691716', '2016-12-07 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('7094ad77-d5e0-4233-bff6-62f22703357a', '2016-12-07 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('342b23a2-db4e-43cd-ae36-67a34adc32c3', '2016-12-07 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('e377241d-9205-4c09-8c60-f42dba16e945', '2016-12-07 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3624421a-37ff-449c-b83e-bd5c780bb54b', '2016-12-07 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('93627c4f-329c-46ea-94ea-bbe268d2d37a', '2016-12-07 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('e34fb614-3950-420d-b6e8-3af3ca91c47d', '2016-12-07 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('e97bf96d-90ba-4adf-b8e4-193e5b03e559', '2016-12-07 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('fc4c7fae-f6b7-4c97-a3fd-2404cc4d0ee6', '2016-12-08 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('fa3484b3-5df8-40e8-b49c-c146388c9f70', '2016-12-08 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('2e0a5f8a-8848-46ee-b07c-8b9300ac97a7', '2016-12-08 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('31c57097-7d9d-459e-8f49-c33054d4ecad', '2016-12-08 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('afe664bf-a45a-41b2-b47b-85db25fbeb09', '2016-12-08 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('9dbff9a7-40e2-4914-8a5a-2e63cd1bee43', '2016-12-08 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('bf52670f-bd15-4f08-ada5-db848e395e80', '2016-12-08 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3f6f678f-19a2-4e77-80f6-9b120621c0b5', '2016-12-08 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('527258b4-e311-4fcb-9088-169a9c8d6259', '2016-12-08 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('704c2d7d-f6f4-40cb-8bc2-bf4b394a45d4', '2016-12-08 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4edfe124-9792-4eca-b514-2ce64eb036f9', '2016-12-08 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('0d1144fe-c247-4d9c-bfd5-70a9ad17d541', '2016-12-08 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('9c323777-7ff2-41f8-b005-d8d460491afc', '2016-12-09 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('0db766c2-78a7-492d-87b9-70106251616f', '2016-12-09 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('b981b6f1-fe82-41db-9826-66dbbadeb6e8', '2016-12-09 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('eba5abef-82f6-403a-8064-4202897e4a0b', '2016-12-09 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('50800bb0-d363-4ceb-8f10-0e93120304d6', '2016-12-09 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('e18e602e-c23a-43f1-853c-7d765d7f7f1a', '2016-12-09 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('186dece1-435c-4b21-aa9d-1cf6d2fa6a5e', '2016-12-09 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('987d20c3-1a83-4c9f-bf3f-8318271c5a4d', '2016-12-09 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('5ccdbee4-e3e6-4df2-92cd-8c1fd44d969d', '2016-12-09 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('9395fe70-98e7-408a-986e-bb205ded4a74', '2016-12-09 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('900e35bf-21d7-4ede-9775-365dfcb8d9d8', '2016-12-09 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('049f2d7e-6959-47bc-b652-b3540cba1d19', '2016-12-09 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('69d4d23b-7839-4f10-aa97-691a6b925167', '2016-12-11 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('87ed97dd-38c6-44bc-8b8d-75772e6ee7f0', '2016-12-11 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('17ea78cb-c589-448e-aed2-12d0e00ae34f', '2016-12-11 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('28ab16aa-e665-480e-aef8-617bad817b13', '2016-12-11 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('d18da9d8-2c58-4537-ac4e-bd94b1d95487', '2016-12-11 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4e9c0195-e115-41a0-adcf-ff44dfdca556', '2016-12-11 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ebf9b99c-18bb-453a-868a-3b7a648bc9e1', '2016-12-11 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f91434a5-c281-4eac-b0c6-0ad3b5017f62', '2016-12-11 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a932265a-5bf2-40cf-87d4-99f453734f79', '2016-12-11 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3b4bee69-1a14-4639-8e65-3375cf796092', '2016-12-11 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('03f28a21-d4d6-4e58-b2d0-624d6f8ada62', '2016-12-11 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('5dceb90f-ed00-4794-9a41-21401124f348', '2016-12-11 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ebfe1bed-3d72-4018-b5fd-8756697ec3e4', '2016-12-12 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ac57eb64-3b6d-4981-9655-21f1333be93d', '2016-12-12 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('af116bda-8d6b-48a6-a922-3fa95dbead67', '2016-12-12 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('526b234f-cd84-46b6-ac6e-31ede1e2b1df', '2016-12-12 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('87aa06da-8cd4-48ec-a719-4724176f7742', '2016-12-12 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cfdab845-ad39-40cb-9a46-986ed992a56d', '2016-12-12 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('7caa980a-e7a0-4477-827e-f98b955ca44d', '2016-12-12 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('844d97cb-715e-4ac7-b121-9dc9c5b9bdf2', '2016-12-12 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f958e7a2-c90b-4526-9b07-469b524bed79', '2016-12-12 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('17a00068-bddb-475d-9408-40d3d01bccad', '2016-12-12 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('32475b20-90fa-41ae-8ee0-d8966cbc0987', '2016-12-12 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8fcdedcd-1ea3-4b30-8ce6-be6173bfb960', '2016-12-12 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('30248308-1f42-4ddd-b480-9be54dae2348', '2016-12-13 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('db58d101-3c5d-466f-913f-b6117c4ed6e8', '2016-12-13 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('263720e5-e1ed-4afa-9abc-31a1cfc853ab', '2016-12-13 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cddb7c25-0e28-41f3-a78d-77fd269e8f1c', '2016-12-13 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f16154a5-a8e6-44de-9900-2101b12bbb63', '2016-12-13 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('d536fe5f-1eff-46dc-8c61-5179cfd21490', '2016-12-13 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c0a02155-2382-41f7-978d-6c18b446c849', '2016-12-13 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('1262b555-0bba-4aca-8ae5-06c2777a4be8', '2016-12-13 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('b2167571-8e22-47c4-9b0a-f1ee9d96bf53', '2016-12-13 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cd2ece29-5862-4ea3-9593-6dcbd444427e', '2016-12-13 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('601d8169-1563-4ad1-ae8a-7a4d48e25028', '2016-12-13 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('0a17c0e3-53dc-4056-85e2-da56ddc35b52', '2016-12-13 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('589923ad-dfc7-45ee-b96d-97b5bcd54e70', '2016-12-14 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('5b65a766-0159-4d97-8716-e69453faf29c', '2016-12-14 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('0849234b-1019-454b-8f7c-cf97fad929d7', '2016-12-14 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4012e83f-522d-4238-95c9-0c99054931e8', '2016-12-14 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ec0abc5d-cad6-48ae-b4fa-56beaaca4856', '2016-12-14 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('2a4c14c8-30c0-40f5-b872-df3b9d0d0303', '2016-12-14 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8e8a9579-d1a2-45ec-892f-bd40754b4924', '2016-12-14 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('675fe24f-d69b-49f7-a249-7331cc21bc4c', '2016-12-14 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('d3cef642-278b-40ec-a5c9-a2cb9886799f', '2016-12-14 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('97355728-033d-421b-9413-a9a686bfc277', '2016-12-14 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3a2a47a6-f339-4997-8901-2ad61785430b', '2016-12-14 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c339ba3b-ebf1-4c1e-aa90-6af47de93ac4', '2016-12-14 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('fdcfb8d5-0cd0-4a4b-8716-22ddd0dc31ac', '2016-12-15 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('67b36b0f-9238-4fea-810a-aa007b00e84c', '2016-12-15 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f96d4b48-b278-48fa-a4e7-b4236b63ddd1', '2016-12-15 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('1ed5fec6-074a-49e2-9bf7-0747363c875a', '2016-12-15 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('403bb882-e41a-4578-ab84-8e3793bf4e0c', '2016-12-15 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('23427dab-cf2d-48ee-88dc-946b16affc4e', '2016-12-15 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('e4c8a60c-7dc8-4ff6-aa3c-f579a20616a4', '2016-12-15 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('857402a8-c55b-42d5-8c5d-b2438ef8b7b1', '2016-12-15 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('60c3ac99-f209-4302-85b5-46a30f40c47d', '2016-12-15 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('92d83025-8e54-4400-9953-6d751510f094', '2016-12-15 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a8c58d24-89d5-4aa1-a77f-e00b541ba23c', '2016-12-15 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f3c0d7cd-2163-4fbd-96ec-9752b698b46e', '2016-12-15 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('39cbaadc-a691-43cb-8c71-cfc361ff0371', '2016-12-16 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('080c47f4-b3d5-4d72-8e89-ba05e58b04ca', '2016-12-16 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8dd8c9e4-ef95-4d14-9f03-d771ca2de8ec', '2016-12-16 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('0166e316-d050-4b5f-af55-02d5a247b507', '2016-12-16 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('29e5269e-95f8-4d6d-bcde-539215192407', '2016-12-16 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('1152d061-3157-4eb1-a4df-1aa692cb7e1c', '2016-12-16 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('dac54410-8438-4884-927b-2c729a521f7e', '2016-12-16 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8ec193c1-47db-4f39-8e01-17bca1aa9d14', '2016-12-16 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('49886614-7cf0-45ce-a725-f52cc97cabeb', '2016-12-16 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('244a4e62-7cd3-4d16-b515-c3691838efd8', '2016-12-16 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3fe1e93a-575b-42aa-a50a-f653b723b322', '2016-12-16 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('22df89a8-56cf-4154-a3e5-820016fce0cc', '2016-12-16 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cf327524-0a0a-4dcb-8dd1-36b1ba3fa8d6', '2016-12-18 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('78d32260-2c51-44d8-889b-75423fae5e05', '2016-12-18 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('16db3b0b-5cd2-4bcd-b7e3-b882cdb9ada9', '2016-12-18 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6a630cf8-f7d2-4413-b0e9-2d1698f89b3c', '2016-12-18 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f538c0c2-2479-4bfd-a212-e76c1d0469ad', '2016-12-18 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cd991e15-1cd5-4a00-8dc1-2351475fd9b1', '2016-12-18 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('688c38dc-62f1-43af-88ed-1a0032a427a2', '2016-12-18 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('daeae982-e72e-4090-88fe-6b20e1278ce4', '2016-12-18 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('473af2fe-6145-4c44-baea-691476a0083f', '2016-12-18 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('08ddfdf8-d6a4-4e43-a982-ad9fe488fbfd', '2016-12-18 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('e310117d-609b-4e68-bf1c-a6b665dae13e', '2016-12-18 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a10f13cc-5d3d-4c63-8fe3-e7ad70157316', '2016-12-18 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('86a8e448-5c4c-44b5-a1fd-652b1c83665e', '2016-12-19 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('36b53211-b981-4878-8354-01d53f31dadf', '2016-12-19 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('318867e7-a86f-4182-9146-06b2bc512d9f', '2016-12-19 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ff57a0b8-35c4-4cf2-85d7-de92b0379903', '2016-12-19 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('042ac08d-dbbc-416e-a0ac-46c07559ea09', '2016-12-19 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6fb9ce29-ecf1-49a2-9aea-cec18b6fa637', '2016-12-19 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3ba16034-037d-4053-960d-47a9b3020e1a', '2016-12-19 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('5350a5cf-59a0-4375-b873-d372b0fd789b', '2016-12-19 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cc127b65-d2b6-4182-8c49-bf17e38f1003', '2016-12-19 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('e5e369fa-5f7f-46de-a0b7-15f2d902cfda', '2016-12-19 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('2ba57d50-4f48-4568-b708-6889423da75a', '2016-12-19 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('dfc5b4b6-858e-4fcc-b867-c7b2386922f0', '2016-12-19 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6f9f46d4-2d6e-4437-8f04-da27e4e2b9a7', '2016-12-20 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('adc708c2-fea8-4996-956f-04148cd40c6f', '2016-12-20 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('badf423c-ef42-4f83-87c0-594dc21d227a', '2016-12-20 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('56afbbe9-19bf-45a1-bc77-1c41edcad926', '2016-12-20 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('cbf75a4f-f936-45b5-908e-48d65c96cef2', '2016-12-20 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('97c48822-8298-4a24-bfbd-37759f2c007a', '2016-12-20 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8f3bff70-6ecd-4b2c-89dc-d3e157e26c1b', '2016-12-20 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('75ce4a36-97d8-483d-b47d-6263dd4c724d', '2016-12-20 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('b2a5c013-4624-4fc3-8d24-36ee5ba83fc2', '2016-12-20 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('7a109fd7-c0b2-4312-9028-bcd77d8514f3', '2016-12-20 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('03fb0830-529f-4c24-b454-a186634f37b3', '2016-12-20 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('001508b2-b4d2-41b6-b42d-b12d6efb53a4', '2016-12-20 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4126e079-20dc-4e4d-bfb5-6737f47df53b', '2016-12-21 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a31e889a-700a-40ef-8223-dd8cfdeb7e17', '2016-12-21 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('be67425e-55d4-4eec-abe8-dcee37290e75', '2016-12-21 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('db6be38e-153e-4682-9780-64ca80b92892', '2016-12-21 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('d4339a2e-3035-4972-8fbc-2c9c796c15b3', '2016-12-21 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4a659448-27c1-4e69-ac43-a59dba0364ff', '2016-12-21 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('16da5427-bf93-49b4-8667-d29392bf3487', '2016-12-21 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('742a4b99-ee49-40f2-8484-9367ae838dc5', '2016-12-21 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('94c05518-dd92-4347-8bc1-67dffb1a9820', '2016-12-21 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('453590a0-d2cc-4323-9165-9f458572a427', '2016-12-21 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('338f3dc4-e73b-4913-8789-8bab649c9eaf', '2016-12-21 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c0fd0eaf-9d39-4ed9-a85f-861e679a85d6', '2016-12-21 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4c6f8c95-174a-4096-bf14-821f88613357', '2016-12-22 00:00:00', '26716c50-5b02-437b-805f-0bbc5066da51', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('9d977f31-6a21-42c8-a977-c4d45cff250e', '2016-12-22 00:00:00', '7020f09f-0663-48c8-8fb0-d4d6ac7a43db', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4234b9bd-b85f-4d44-b7fd-2723354a11f5', '2016-12-22 00:00:00', '74b17b29-f87b-4c6a-ab70-839dac3525eb', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c4d9047a-c0f0-4c82-940e-55e6e8ce3fc3', '2016-12-22 00:00:00', 'aa350bea-9505-4006-8973-1d7731d4497c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('067dc822-88e2-41fe-bd74-3aa1a1bb3aab', '2016-12-22 00:00:00', '7f26cf5a-8902-4a4b-8399-ed8a4a3e2769', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('1865eb2f-c825-42af-88f0-2b2d03de6dde', '2016-12-22 00:00:00', '17501dd1-1d43-4dd6-b7ea-85306c69cd60', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('acfb0c33-3a4f-4d5a-b8d0-820dd9a59c1f', '2016-12-22 00:00:00', '1005ca8c-bfb7-4f97-a49e-15c07fefed71', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c237097e-6424-4897-af45-c99a2fe753ed', '2016-12-22 00:00:00', 'a47e07c6-d366-4cd0-b767-11d45e6acc59', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('4a428ace-045f-45cf-be0e-3280533d76b1', '2016-12-22 00:00:00', '9ba095d6-c904-4fc6-9a8e-641f62605084', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('10006931-80f0-4ec8-9995-57898d4d25e4', '2016-12-22 00:00:00', '79fbeafc-15c7-4d8c-af36-d6de2a131d5c', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('8cd6f506-521c-4ec7-8c3b-c890e4cc7ffd', '2016-12-22 00:00:00', '663cc264-6b7e-4c80-ab3c-fcba301b70a8', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('5273ce38-24db-44e5-ba0a-6d21fcb69e68', '2016-12-22 00:00:00', 'd0e28b6a-1920-4d91-9da5-ca57441a36ea', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('b61f49c6-fa6b-49f2-adf1-8539983eb898', '2016-11-26 00:00:00', 'a7ae0f1f-2640-4b6f-a335-35d62cec1277', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('9c4a7f5a-385a-4e13-929a-de81ea5c8736', '2016-11-26 00:00:00', 'd158531b-9155-417b-b396-031cb576d8d0', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('fd30d0f2-b113-4419-9b89-c585a9d15566', '2016-11-26 00:00:00', '6bae679c-5ad9-4507-b753-48898d4f4861', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('0d26d3cb-05ac-4a92-965e-9fd9296d0b1a', '2016-11-26 00:00:00', 'e758cb53-962b-4c13-a24b-10e7c35a6f94', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('be249e8b-0e7e-424f-afd6-d93d9eaf8a31', '2016-11-26 00:00:00', 'a70692f8-2018-4114-9b54-fc3a74e0e420', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c1659903-9140-48de-aecf-97ca23e5b4a0', '2016-11-26 00:00:00', '392d176e-b632-4ddd-b5cc-9ba53a6c68e3', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('bb7857c6-32cb-4f8d-a67a-3fbe95212af7', '2016-11-26 00:00:00', '636b0918-b35f-49c0-9b2f-eee47a4941bc', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f8c1888d-7ca8-4f2a-84f8-708c481b5998', '2016-12-03 00:00:00', 'a7ae0f1f-2640-4b6f-a335-35d62cec1277', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ec7bb608-d8d8-4213-bec2-7cd17bec3bd3', '2016-12-03 00:00:00', 'd158531b-9155-417b-b396-031cb576d8d0', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('3c329527-5850-47db-a49d-28d58eb90575', '2016-12-03 00:00:00', '6bae679c-5ad9-4507-b753-48898d4f4861', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c99ba9af-9517-43b1-a7d5-b97b0aa63c69', '2016-12-03 00:00:00', 'e758cb53-962b-4c13-a24b-10e7c35a6f94', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('316ea876-c2cb-48e7-9af4-afff4d1ca2ae', '2016-12-03 00:00:00', 'a70692f8-2018-4114-9b54-fc3a74e0e420', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('47c6ed08-546d-4a76-a570-75cad5375496', '2016-12-03 00:00:00', '392d176e-b632-4ddd-b5cc-9ba53a6c68e3', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('a12f1c6a-2c18-447a-bb6a-11d08d826afd', '2016-12-03 00:00:00', '636b0918-b35f-49c0-9b2f-eee47a4941bc', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('882b8297-a486-4d69-adad-ee43be312b49', '2016-12-10 00:00:00', 'a7ae0f1f-2640-4b6f-a335-35d62cec1277', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('1f109cae-6952-42f5-95dd-b0294ee2d98c', '2016-12-10 00:00:00', 'd158531b-9155-417b-b396-031cb576d8d0', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ec5aa25e-4182-4494-abab-1ed0b36a34ee', '2016-12-10 00:00:00', '6bae679c-5ad9-4507-b753-48898d4f4861', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('c2a88cfc-7397-4b38-876f-50356c4a6d54', '2016-12-10 00:00:00', 'e758cb53-962b-4c13-a24b-10e7c35a6f94', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('2bc46f34-7a5a-4cec-af9a-52695deabf25', '2016-12-10 00:00:00', 'a70692f8-2018-4114-9b54-fc3a74e0e420', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('7c283552-a266-465a-b4ae-f227fae8a79d', '2016-12-10 00:00:00', '392d176e-b632-4ddd-b5cc-9ba53a6c68e3', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('b8b9d132-f844-4835-bf69-8d320e5c5088', '2016-12-10 00:00:00', '636b0918-b35f-49c0-9b2f-eee47a4941bc', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('065aaee3-8bea-477d-a34f-91d8aa9c4f36', '2016-12-17 00:00:00', 'a7ae0f1f-2640-4b6f-a335-35d62cec1277', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('1e89a885-2c59-4475-aee7-de30e8db6225', '2016-12-17 00:00:00', 'd158531b-9155-417b-b396-031cb576d8d0', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('69207dc3-43a0-457f-a5f2-8a5b9507418f', '2016-12-17 00:00:00', '6bae679c-5ad9-4507-b753-48898d4f4861', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('6a3a21d0-2b57-49c3-ad0e-57d878d43fbe', '2016-12-17 00:00:00', 'e758cb53-962b-4c13-a24b-10e7c35a6f94', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('f9ef1401-920f-406e-a4ed-bdf0a9dd0410', '2016-12-17 00:00:00', 'a70692f8-2018-4114-9b54-fc3a74e0e420', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('ac176502-4009-414e-b8a6-3d247dff8e29', '2016-12-17 00:00:00', '392d176e-b632-4ddd-b5cc-9ba53a6c68e3', '9e3440cf-b6b5-44b5-9427-a2215f792336');
INSERT INTO viaje VALUES ('7b8f9bfc-b85b-43ee-b775-edc297a67490', '2016-12-17 00:00:00', '636b0918-b35f-49c0-9b2f-eee47a4941bc', '9e3440cf-b6b5-44b5-9427-a2215f792336');


INSERT INTO pasaje VALUES ('2c323206-89a1-45aa-a54d-f47eb276bf30', 3, '1827391-2', false, '2016-11-23 00:00:00', NULL, NULL, NULL, '26737d91-16c9-4d99-ba35-0fd44dece1c0', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', '333ec2fc-8737-4e8d-ba68-f47b3c11adb4', NULL, '2218d299-373d-4194-89c5-738732fff657');
