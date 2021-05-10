package edu.depauw.emulator_ide.assembler;

import java.util.HashMap;

import edu.depauw.emulator_ide.common.Position;

public class Token{

    enum Type{
	STRING,
	NUM,
	DIRECTIVE,
	IDENT,
	LABEL,

	R0,
	R1, //Register names
	R2,
	R3,
	R4,
	R5,
	R6,
	R7,
	R8,
	R9,
	R10,
	R11,
	R12,
	R13,
	R14,
	R15,
	CPSR,
	SPSR, //not used but just here for completeness
	
	//Instructions -- Continues for a while these are sorted by their instruction type
	
	//BX Instruction
	BX,
	BXEQ,
	BXNE,
	BXCS,
	BXCC,
	BXMI,
	BXPL,
	BXVS,
	BXVC,
	BXHI,
	BXLS,
	BXGE,
	BXLT,
	BXGT,
	BXLE,
	BXAL,

	//B/BL Instruction
	B,
	BEQ,
	BNE,
	BCS,
	BCC,
	BMI,
	BPL,
	BVS,
	BVC,
	BHI,
	BLS,
	BGE,
	BLT,
	BGT,
	BLE,
	BAL,
	
	BL,
	BLEQ,
	BLNE,
	BLCS,
	BLCC,
	BLMI,
	BLPL,
	BLVS,
	BLVC,
	BLHI,
	BLLS,
	BLGE,
	BLLT,
	BLGT,
	BLLE,
	BLAL,
	
	//Single op data processsing instruction
	//MOV
	MOV,
	MOVEQ,
	MOVNE,
	MOVCS,
	MOVCC,
	MOVMI,
	MOVPL,
	MOVVS,
	MOVVC,
	MOVHI,
	MOVLS,
	MOVGE,
	MOVLT,
	MOVGT,
	MOVLE,
	MOVAL,
	
	//MOV
	MOVS,
	MOVEQS,
	MOVNES,
	MOVCSS,
	MOVCCS,
	MOVMIS,
	MOVPLS,
	MOVVSS,
	MOVVCS,
	MOVHIS,
	MOVLSS,
	MOVGES,
	MOVLTS,
	MOVGTS,
	MOVLES,
	MOVALS,
	//MVN
	MVN,
	MVNEQ,
	MVNNE,
	MVNCS,
	MVNCC,
	MVNMI,
	MVNPL,
	MVNVS,
	MVNVC,
	MVNHI,
	MVNLS,
	MVNGE,
	MVNLT,
	MVNGT,
	MVNLE,
	MVNAL,
	
	MVNS,
	MVNEQS,
	MVNNES,
	MVNCSS,
	MVNCCS,
	MVNMIS,
	MVNPLS,
	MVNVSS,
	MVNVCS,
	MVNHIS,
	MVNLSS,
	MVNGES,
	MVNLTS,
	MVNGTS,
	MVNLES,
	MVNALS,
	
	//CMP
	CMP,
	CMPEQ,
	CMPNE,
	CMPCS,
	CMPCC,
	CMPMI,
	CMPPL,
	CMPVS,
	CMPVC,
	CMPHI,
	CMPLS,
	CMPGE,
	CMPLT,
	CMPGT,
	CMPLE,
	CMPAL,
	
	//CMN
	CMN,
	CMNEQ,
	CMNNE,
	CMNCS,
	CMNCC,
	CMNMI,
	CMNPL,
	CMNVS,
	CMNVC,
	CMNHI,
	CMNLS,
	CMNGE,
	CMNLT,
	CMNGT,
	CMNLE,
	CMNAL,
	
	//TEQ
	TEQ,
	TEQEQ,
	TEQNE,
	TEQCS,
	TEQCC,
	TEQMI,
	TEQPL,
	TEQVS,
	TEQVC,
	TEQHI,
	TEQLS,
	TEQGE,
	TEQLT,
	TEQGT,
	TEQLE,
	TEQAL,
	
	//TST
	TST,
	TSTEQ,
	TSTNE,
	TSTCS,
	TSTCC,
	TSTMI,
	TSTPL,
	TSTVS,
	TSTVC,
	TSTHI,
	TSTLS,
	TSTGE,
	TSTLT,
	TSTGT,
	TSTLE,
	TSTAL,
	
	//AND
	AND,
	ANDEQ,
	ANDNE,
	ANDCS,
	ANDCC,
	ANDMI,
	ANDPL,
	ANDVS,
	ANDVC,
	ANDHI,
	ANDLS,
	ANDGE,
	ANDLT,
	ANDGT,
	ANDLE,
	ANDAL,
	
	ANDS,
	ANDEQS,
	ANDNES,
	ANDCSS,
	ANDCCS,
	ANDMIS,
	ANDPLS,
	ANDVSS,
	ANDVCS,
	ANDHIS,
	ANDLSS,
	ANDGES,
	ANDLTS,
	ANDGTS,
	ANDLES,
	ANDALS,
	
	//EOR
	EOR,
	EOREQ,
	EORNE,
	EORCS,
	EORCC,
	EORMI,
	EORPL,
	EORVS,
	EORVC,
	EORHI,
	EORLS,
	EORGE,
	EORLT,
	EORGT,
	EORLE,
	EORAL,
	
	EORS,
	EOREQS,
	EORNES,
	EORCSS,
	EORCCS,
	EORMIS,
	EORPLS,
	EORVSS,
	EORVCS,
	EORHIS,
	EORLSS,
	EORGES,
	EORLTS,
	EORGTS,
	EORLES,
	EORALS,
	
	//SUB
	SUB,
	SUBEQ,
	SUBNE,
	SUBCS,
	SUBCC,
	SUBMI,
	SUBPL,
	SUBVS,
	SUBVC,
	SUBHI,
	SUBLS,
	SUBGE,
	SUBLT,
	SUBGT,
	SUBLE,
	SUBAL,
	
	SUBS,
	SUBEQS,
	SUBNES,
	SUBCSS,
	SUBCCS,
	SUBMIS,
	SUBPLS,
	SUBVSS,
	SUBVCS,
	SUBHIS,
	SUBLSS,
	SUBGES,
	SUBLTS,
	SUBGTS,
	SUBLES,
	SUBALS,
	
	//RSB
	RSB,
	RSBEQ,
	RSBNE,
	RSBCS,
	RSBCC,
	RSBMI,
	RSBPL,
	RSBVS,
	RSBVC,
	RSBHI,
	RSBLS,
	RSBGE,
	RSBLT,
	RSBGT,
	RSBLE,
	RSBAL,
	
	RSBS,
	RSBEQS,
	RSBNES,
	RSBCSS,
	RSBCCS,
	RSBMIS,
	RSBPLS,
	RSBVSS,
	RSBVCS,
	RSBHIS,
	RSBLSS,
	RSBGES,
	RSBLTS,
	RSBGTS,
	RSBLES,
	RSBALS,
	
	//ADD
	ADD,
	ADDEQ,
	ADDNE,
	ADDCS,
	ADDCC,
	ADDMI,
	ADDPL,
	ADDVS,
	ADDVC,
	ADDHI,
	ADDLS,
	ADDGE,
	ADDLT,
	ADDGT,
	ADDLE,
	ADDAL,
	
	ADDS,
	ADDEQS,
	ADDNES,
	ADDCSS,
	ADDCCS,
	ADDMIS,
	ADDPLS,
	ADDVSS,
	ADDVCS,
	ADDHIS,
	ADDLSS,
	ADDGES,
	ADDLTS,
	ADDGTS,
	ADDLES,
	ADDALS,
	
	//ADC
	ADC,
	ADCEQ,
	ADCNE,
	ADCCS,
	ADCCC,
	ADCMI,
	ADCPL,
	ADCVS,
	ADCVC,
	ADCHI,
	ADCLS,
	ADCGE,
	ADCLT,
	ADCGT,
	ADCLE,
	ADCAL,

	ADCS,
	ADCEQS,
	ADCNES,
	ADCCSS,
	ADCCCS,
	ADCMIS,
	ADCPLS,
	ADCVSS,
	ADCVCS,
	ADCHIS,
	ADCLSS,
	ADCGES,
	ADCLTS,
	ADCGTS,
	ADCLES,
	ADCALS,
	
	//SBC
	SBC,
	SBCEQ,
	SBCNE,
	SBCCS,
	SBCCC,
	SBCMI,
	SBCPL,
	SBCVS,
	SBCVC,
	SBCHI,
	SBCLS,
	SBCGE,
	SBCLT,
	SBCGT,
	SBCLE,
	SBCAL,
	
	SBCS,
	SBCEQS,
	SBCNES,
	SBCCSS,
	SBCCCS,
	SBCMIS,
	SBCPLS,
	SBCVSS,
	SBCVCS,
	SBCHIS,
	SBCLSS,
	SBCGES,
	SBCLTS,
	SBCGTS,
	SBCLES,
	SBCALS,
	
	//RSC
	RSC,
	RSCEQ,
	RSCNE,
	RSCCS,
	RSCCC,
	RSCMI,
	RSCPL,
	RSCVS,
	RSCVC,
	RSCHI,
	RSCLS,
	RSCGE,
	RSCLT,
	RSCGT,
	RSCLE,
	RSCAL,
	
	RSCS,
	RSCEQS,
	RSCNES,
	RSCCSS,
	RSCCCS,
	RSCMIS,
	RSCPLS,
	RSCVSS,
	RSCVCS,
	RSCHIS,
	RSCLSS,
	RSCGES,
	RSCLTS,
	RSCGTS,
	RSCLES,
	RSCALS,
	
	//ORR
	ORR,
	ORREQ,
	ORRNE,
	ORRCS,
	ORRCC,
	ORRMI,
	ORRPL,
	ORRVS,
	ORRVC,
	ORRHI,
	ORRLS,
	ORRGE,
	ORRLT,
	ORRGT,
	ORRLE,
	ORRAL,
	
	ORRS,
	ORREQS,
	ORRNES,
	ORRCSS,
	ORRCCS,
	ORRMIS,
	ORRPLS,
	ORRVSS,
	ORRVCS,
	ORRHIS,
	ORRLSS,
	ORRGES,
	ORRLTS,
	ORRGTS,
	ORRLES,
	ORRALS,
	
	//BIC
	BIC,
	BICEQ,
	BICNE,
	BICCS,
	BICCC,
	BICMI,
	BICPL,
	BICVS,
	BICVC,
	BICHI,
	BICLS,
	BICGE,
	BICLT,
	BICGT,
	BICLE,
	BICAL,
	
	BICS,
	BICEQS,
	BICNES,
	BICCSS,
	BICCCS,
	BICMIS,
	BICPLS,
	BICVSS,
	BICVCS,
	BICHIS,
	BICLSS,
	BICGES,
	BICLTS,
	BICGTS,
	BICLES,
	BICALS,
	
	//MRS
	MRS,
	MRSEQ,
	MRSNE,
	MRSCS,
	MRSCC,
	MRSMI,
	MRSPL,
	MRSVS,
	MRSVC,
	MRSHI,
	MRSLS,
	MRSGE,
	MRSLT,
	MRSGT,
	MRSLE,
	MRSAL,
	
	//MSR
	MSR,
	MSREQ,
	MSRNE,
	MSRCS,
	MSRCC,
	MSRMI,
	MSRPL,
	MSRVS,
	MSRVC,
	MSRHI,
	MSRLS,
	MSRGE,
	MSRLT,
	MSRGT,
	MSRLE,
	MSRAL,
	
	//MUL
	MUL,
	MULEQ,
	MULNE,
	MULCS,
	MULCC,
	MULMI,
	MULPL,
	MULVS,
	MULVC,
	MULHI,
	MULLS,
	MULGE,
	MULLT,
	MULGT,
	MULLE,
	MULAL,
	
	MULS,
	MULEQS,
	MULNES,
	MULCSS,
	MULCCS,
	MULMIS,
	MULPLS,
	MULVSS,
	MULVCS,
	MULHIS,
	MULLSS,
	MULGES,
	MULLTS,
	MULGTS,
	MULLES,
	MULALS,
	
	//MLA
	MLA,
	MLAEQ,
	MLANE,
	MLACS,
	MLACC,
	MLAMI,
	MLAPL,
	MLAVS,
	MLAVC,
	MLAHI,
	MLALS,
	MLAGE,
	MLALT,
	MLAGT,
	MLALE,
	MLAAL,
	
	MLAS,
	MLAEQS,
	MLANES,
	MLACSS,
	MLACCS,
	MLAMIS,
	MLAPLS,
	MLAVSS,
	MLAVCS,
	MLAHIS,
	MLALSS,
	MLAGES,
	MLALTS,
	MLAGTS,
	MLALES,
	MLAALS,
	
	//MUL
	UMULL,
	UMULLEQ,
	UMULLNE,
	UMULLCS,
	UMULLCC,
	UMULLMI,
	UMULLPL,
	UMULLVS,
	UMULLVC,
	UMULLHI,
	UMULLLS,
	UMULLGE,
	UMULLLT,
	UMULLGT,
	UMULLLE,
	UMULLAL,
	
	UMULLS,
	UMULLEQS,
	UMULLNES,
	UMULLCSS,
	UMULLCCS,
	UMULLMIS,
	UMULLPLS,
	UMULLVSS,
	UMULLVCS,
	UMULLHIS,
	UMULLLSS,
	UMULLGES,
	UMULLLTS,
	UMULLGTS,
	UMULLLES,
	UMULLALS,
	
	SMULL,
	SMULLEQ,
	SMULLNE,
	SMULLCS,
	SMULLCC,
	SMULLMI,
	SMULLPL,
	SMULLVS,
	SMULLVC,
	SMULLHI,
	SMULLLS,
	SMULLGE,
	SMULLLT,
	SMULLGT,
	SMULLLE,
	SMULLAL,
	
	SMULLS,
	SMULLEQS,
	SMULLNES,
	SMULLCSS,
	SMULLCCS,
	SMULLMIS,
	SMULLPLS,
	SMULLVSS,
	SMULLVCS,
	SMULLHIS,
	SMULLLSS,
	SMULLGES,
	SMULLLTS,
	SMULLGTS,
	SMULLLES,
	SMULLALS,
	
	//MLAL
	UMLAL,
	UMLALEQ,
	UMLALNE,
	UMLALCS,
	UMLALCC,
	UMLALMI,
	UMLALPL,
	UMLALVS,
	UMLALVC,
	UMLALHI,
	UMLALLS,
	UMLALGE,
	UMLALLT,
	UMLALGT,
	UMLALLE,
	UMLALAL,
	
	UMLALS,
	UMLALEQS,
	UMLALNES,
	UMLALCSS,
	UMLALCCS,
	UMLALMIS,
	UMLALPLS,
	UMLALVSS,
	UMLALVCS,
	UMLALHIS,
	UMLALLSS,
	UMLALGES,
	UMLALLTS,
	UMLALGTS,
	UMLALLES,
	UMLALALS,
	
	SMLAL,
	SMLALEQ,
	SMLALNE,
	SMLALCS,
	SMLALCC,
	SMLALMI,
	SMLALPL,
	SMLALVS,
	SMLALVC,
	SMLALHI,
	SMLALLS,
	SMLALGE,
	SMLALLT,
	SMLALGT,
	SMLALLE,
	SMLALAL,
	
	SMLALS,
	SMLALEQS,
	SMLALNES,
	SMLALCSS,
	SMLALCCS,
	SMLALMIS,
	SMLALPLS,
	SMLALVSS,
	SMLALVCS,
	SMLALHIS,
	SMLALLSS,
	SMLALGES,
	SMLALLTS,
	SMLALGTS,
	SMLALLES,
	SMLALALS,
	
	//LDR
	LDR,
	LDREQ,
	LDRNE,
	LDRCS,
	LDRCC,
	LDRMI,
	LDRPL,
	LDRVS,
	LDRVC,
	LDRHI,
	LDRLS,
	LDRGE,
	LDRLT,
	LDRGT,
	LDRLE,
	LDRAL,
	
	LDRB,
	LDREQB,
	LDRNEB,
	LDRCSB,
	LDRCCB,
	LDRMIB,
	LDRPLB,
	LDRVSB,
	LDRVCB,
	LDRHIB,
	LDRLSB,
	LDRGEB,
	LDRLTB,
	LDRGTB,
	LDRLEB,
	LDRALB,
	
	LDRT,
	LDREQT,
	LDRNET,
	LDRCST,
	LDRCCT,
	LDRMIT,
	LDRPLT,
	LDRVST,
	LDRVCT,
	LDRHIT,
	LDRLST,
	LDRGET,
	LDRLTT,
	LDRGTT,
	LDRLET,
	LDRALT,
	
	LDRBT,
	LDREQBT,
	LDRNEBT,
	LDRCSBT,
	LDRCCBT,
	LDRMIBT,
	LDRPLBT,
	LDRVSBT,
	LDRVCBT,
	LDRHIBT,
	LDRLSBT,
	LDRGEBT,
	LDRLTBT,
	LDRGTBT,
	LDRLEBT,
	LDRALBT,
	
	//STR
	STR,
	STREQ,
	STRNE,
	STRCS,
	STRCC,
	STRMI,
	STRPL,
	STRVS,
	STRVC,
	STRHI,
	STRLS,
	STRGE,
	STRLT,
	STRGT,
	STRLE,
	STRAL,
	
	STRB,
	STREQB,
	STRNEB,
	STRCSB,
	STRCCB,
	STRMIB,
	STRPLB,
	STRVSB,
	STRVCB,
	STRHIB,
	STRLSB,
	STRGEB,
	STRLTB,
	STRGTB,
	STRLEB,
	STRALB,
	
	STRT,
	STREQT,
	STRNET,
	STRCST,
	STRCCT,
	STRMIT,
	STRPLT,
	STRVST,
	STRVCT,
	STRHIT,
	STRLST,
	STRGET,
	STRLTT,
	STRGTT,
	STRLET,
	STRALT,
	
	STRBT,
	STREQBT,
	STRNEBT,
	STRCSBT,
	STRCCBT,
	STRMIBT,
	STRPLBT,
	STRVSBT,
	STRVCBT,
	STRHIBT,
	STRLSBT,
	STRGEBT,
	STRLTBT,
	STRGTBT,
	STRLEBT,
	STRALBT,
	
	//LDRH
	LDRH,
	LDREQH,
	LDRNEH,
	LDRCSH,
	LDRCCH,
	LDRMIH,
	LDRPLH,
	LDRVSH,
	LDRVCH,
	LDRHIH,
	LDRLSH,
	LDRGEH,
	LDRLTH,
	LDRGTH,
	LDRLEH,
	LDRALH,
	
	//LDRSH
	LDRSH,
	LDREQSH,
	LDRNESH,
	LDRCSSH,
	LDRCCSH,
	LDRMISH,
	LDRPLSH,
	LDRVSSH,
	LDRVCSH,
	LDRHISH,
	LDRLSSH,
	LDRGESH,
	LDRLTSH,
	LDRGTSH,
	LDRLESH,
	LDRALSH,
	
	//LDRSB
	LDRSB,
	LDREQSB,
	LDRNESB,
	LDRCSSB,
	LDRCCSB,
	LDRMISB,
	LDRPLSB,
	LDRVSSB,
	LDRVCSB,
	LDRHISB,
	LDRLSSB,
	LDRGESB,
	LDRLTSB,
	LDRGTSB,
	LDRLESB,
	LDRALSB,
	
	//STRH
	STRH,
	STREQH,
	STRNEH,
	STRCSH,
	STRCCH,
	STRMIH,
	STRPLH,
	STRVSH,
	STRVCH,
	STRHIH,
	STRLSH,
	STRGEH,
	STRLTH,
	STRGTH,
	STRLEH,
	STRALH,
	
	//STRSH
	STRSH,
	STREQSH,
	STRNESH,
	STRCSSH,
	STRCCSH,
	STRMISH,
	STRPLSH,
	STRVSSH,
	STRVCSH,
	STRHISH,
	STRLSSH,
	STRGESH,
	STRLTSH,
	STRGTSH,
	STRLESH,
	STRALSH,
	
	//STRSB
	STRSB,
	STREQSB,
	STRNESB,
	STRCSSB,
	STRCCSB,
	STRMISB,
	STRPLSB,
	STRVSSB,
	STRVCSB,
	STRHISB,
	STRLSSB,
	STRGESB,
	STRLTSB,
	STRGTSB,
	STRLESB,
	STRALSB,
	
	//LDM
	LDMFD,
	LDMEQFD,
	LDMNEFD,
	LDMCSFD,
	LDMCCFD,
	LDMMIFD,
	LDMPLFD,
	LDMVSFD,
	LDMVCFD,
	LDMHIFD,
	LDMLSFD,
	LDMGEFD,
	LDMLTFD,
	LDMGTFD,
	LDMLEFD,
	LDMALFD,
	
	LDMED,
	LDMEQED,
	LDMNEED,
	LDMCSED,
	LDMCCED,
	LDMMIED,
	LDMPLED,
	LDMVSED,
	LDMVCED,
	LDMHIED,
	LDMLSED,
	LDMGEED,
	LDMLTED,
	LDMGTED,
	LDMLEED,
	LDMALED,
	
	LDMFA,
	LDMEQFA,
	LDMNEFA,
	LDMCSFA,
	LDMCCFA,
	LDMMIFA,
	LDMPLFA,
	LDMVSFA,
	LDMVCFA,
	LDMHIFA,
	LDMLSFA,
	LDMGEFA,
	LDMLTFA,
	LDMGTFA,
	LDMLEFA,
	LDMALFA,
	
	LDMEA,
	LDMEQEA,
	LDMNEEA,
	LDMCSEA,
	LDMCCEA,
	LDMMIEA,
	LDMPLEA,
	LDMVSEA,
	LDMVCEA,
	LDMHIEA,
	LDMLSEA,
	LDMGEEA,
	LDMLTEA,
	LDMGTEA,
	LDMLEEA,
	LDMALEA,
	
	LDMIA,
	LDMEQIA,
	LDMNEIA,
	LDMCSIA,
	LDMCCIA,
	LDMMIIA,
	LDMPLIA,
	LDMVSIA,
	LDMVCIA,
	LDMHIIA,
	LDMLSIA,
	LDMGEIA,
	LDMLTIA,
	LDMGTIA,
	LDMLEIA,
	LDMALIA,
	
	LDMIB,
	LDMEQIB,
	LDMNEIB,
	LDMCSIB,
	LDMCCIB,
	LDMMIIB,
	LDMPLIB,
	LDMVSIB,
	LDMVCIB,
	LDMHIIB,
	LDMLSIB,
	LDMGEIB,
	LDMLTIB,
	LDMGTIB,
	LDMLEIB,
	LDMALIB,
	
	LDMDA,
	LDMEDA,
	LDMNEDA,
	LDMCSDA,
	LDMCCDA,
	LDMMIDA,
	LDMPLDA,
	LDMVSDA,
	LDMVCDA,
	LDMHIDA,
	LDMLSDA,
	LDMGEDA,
	LDMLTDA,
	LDMGTDA,
	LDMLEDA,
	LDMALDA,
	
	LDMDB,
	LDMEDB,
	LDMNEDB,
	LDMCSDB,
	LDMCCDB,
	LDMMIDB,
	LDMPLDB,
	LDMVSDB,
	LDMVCDB,
	LDMHIDB,
	LDMLSDB,
	LDMGEDB,
	LDMLTDB,
	LDMGTDB,
	LDMLEDB,
	LDMALDB,
	
	//STM
	STMFD,
	STMEQFD,
	STMNEFD,
	STMCSFD,
	STMCCFD,
	STMMIFD,
	STMPLFD,
	STMVSFD,
	STMVCFD,
	STMHIFD,
	STMLSFD,
	STMGEFD,
	STMLTFD,
	STMGTFD,
	STMLEFD,
	STMALFD,
	
	STMED,
	STMEQED,
	STMNEED,
	STMCSED,
	STMCCED,
	STMMIED,
	STMPLED,
	STMVSED,
	STMVCED,
	STMHIED,
	STMLSED,
	STMGEED,
	STMLTED,
	STMGTED,
	STMLEED,
    STMALED,
	
	STMFA,
	STMEQFA,
	STMNEFA,
	STMCSFA,
	STMCCFA,
	STMMIFA,
	STMPLFA,
	STMVSFA,
	STMVCFA,
	STMHIFA,
	STMLSFA,
	STMGEFA,
	STMLTFA,
	STMGTFA,
	STMLEFA,
	STMALFA,
	
	STMEA,
	STMEQEA,
	STMNEEA,
	STMCSEA,
	STMCCEA,
	STMMIEA,
	STMPLEA,
	STMVSEA,
	STMVCEA,
	STMHIEA,
	STMLSEA,
	STMGEEA,
	STMLTEA,
	STMGTEA,
	STMLEEA,
	STMALEA,
	
	STMIA,
	STMEQIA,
	STMNEIA,
	STMCSIA,
	STMCCIA,
	STMMIIA,
	STMPLIA,
	STMVSIA,
	STMVCIA,
	STMHIIA,
	STMLSIA,
	STMGEIA,
	STMLTIA,
	STMGTIA,
	STMLEIA,
	STMALIA,
	
	STMIB,
	STMEQIB,
	STMNEIB,
	STMCSIB,
	STMCCIB,
	STMMIIB,
	STMPLIB,
	STMVSIB,
	STMVCIB,
	STMHIIB,
	STMLSIB,
	STMGEIB,
	STMLTIB,
	STMGTIB,
	STMLEIB,
	STMALIB,
	
	STMDA,
	STMEDA,
	STMNEDA,
	STMCSDA,
	STMCCDA,
	STMMIDA,
	STMPLDA,
	STMVSDA,
	STMVCDA,
	STMHIDA,
	STMLSDA,
	STMGEDA,
	STMLTDA,
	STMGTDA,
	STMLEDA,
	STMALDA,
	
	STMDB,
	STMEDB,
	STMNEDB,
	STMCSDB,
	STMCCDB,
	STMMIDB,
	STMPLDB,
	STMVSDB,
	STMVCDB,
	STMHIDB,
	STMLSDB,
	STMGEDB,
	STMLTDB,
	STMGTDB,
	STMLEDB,
	STMALDB,
	
	//STW
	STW,
	STWEQ,
	STWNE,
	STWCS,
	STWCC,
	STWMI,
	STWPL,
	STWVS,
	STWVC,
	STWHI,
	STWLS,
	STWGE,
	STWLT,
	STWGT,
	STWLE,
	STWAL,
	
	STWB,
	STWEQB,
	STWNEB,
	STWCSB,
	STWCCB,
	STWMIB,
	STWPLB,
	STWVSB,
	STWVCB,
	STWHIB,
	STWLSB,
	STWGEB,
	STWLTB,
	STWGTB,
	STWLEB,
	STWALB,
	
	//SWI
	SWI,
	SWIEQ,
	SWINE,
	SWICS,
	SWICC,
	SWIMI,
	SWIPL,
	SWIVS,
	SWIVC,
	SWIHI,
	SWILS,
	SWIGE,
	SWILT,
	SWIGT,
	SWILE,
	SWIAL,
	
	//STOP
	STOP,
	
	//Operators
	COMMA,
	EXPL,
	RBRACK,
	LBRACK,
	LCURL,
	RCURL,
	CARROT,
	MINUS,
    }
    
    private static HashMap <String, Token.Type> ops;
    private static HashMap <String, Token.Type> instructions;
    private static HashMap <String, Token.Type> registers;
    
    static {
    	//ops
    	ops = new HashMap<>();
    	ops.put(",", Token.Type.COMMA);
    	ops.put("!", Token.Type.EXPL);
    	ops.put("[", Token.Type.LBRACK);
    	ops.put("]", Token.Type.RBRACK);
    	ops.put("{", Token.Type.LCURL);
    	ops.put("}", Token.Type.RCURL);
    	ops.put("^", Token.Type.CARROT);
    	ops.put("-", Token.Type.MINUS);
    	
    	registers = new HashMap<>();
    	registers.put("R0", Token.Type.R0);
    	registers.put("R1", Token.Type.R1);
    	registers.put("R2", Token.Type.R2);
    	registers.put("R3", Token.Type.R3);
    	registers.put("R4", Token.Type.R4);
    	registers.put("R5", Token.Type.R5);
    	registers.put("R6", Token.Type.R6);
    	registers.put("R7", Token.Type.R7);
    	registers.put("R8", Token.Type.R8);
    	registers.put("R9", Token.Type.R9);
    	registers.put("R10", Token.Type.R10);
    	registers.put("R11", Token.Type.R11);
    	registers.put("R12", Token.Type.R12);
    	registers.put("R13", Token.Type.R13);
    	registers.put("R14", Token.Type.R14);
    	registers.put("R15", Token.Type.R15);
    	registers.put("CPSR", Token.Type.R15);
    	
    	
    }

    private final Token.Type type;
    private final String lexeme;
    private final Position position;
    
    private Token(Token.Type type, String lexeme, Position position){
		this.type = type;
		this.lexeme = lexeme;
		this.position = position;
    }

    public String getLexeme(){
    	return lexeme; //ToDo - I will need to complete this later
    }

    public Position getPosition(){
    	return position;
    }

    public static Token makeNumToken(String lexeme, Position pos){
    	return new Token(Token.Type.NUM, lexeme, pos);
    }

    public static Token makeIdentToken(String lexeme, Position pos){
    	if(registers.containsKey(lexeme)) {
    		return new Token(registers.get(lexeme), lexeme, pos);
    	} else if(instructions.containsKey(lexeme)) {
    		return new Token(instructions.get(lexeme), lexeme, pos);
    	} else {
    		return new Token(Token.Type.IDENT, lexeme, pos);
    	}
    }
    
    public static Token makeLabelToken(String lexeme, Position pos) {
		return new Token(Token.Type.LABEL, lexeme, pos);
    }

    public static Token makeStringToken(String lexeme, Position pos){
	return new Token(Token.Type.STRING, lexeme, pos);
    }

    public static Token makeDirToken(String lexeme, Position pos){
	return new Token(Token.Type.DIRECTIVE, lexeme, pos);
    }
}
