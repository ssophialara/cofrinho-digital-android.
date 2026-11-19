package com.example.cofrinhodigital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF4F6F9) // Fundo cinza-claro suave para destacar o Card
                ) {
                    TelaCofrinho()
                }
            }
        }
    }
}

@Composable
fun TelaCofrinho() {
    // Estados da tela
    var valorGuardado by remember { mutableStateOf(0) }
    val metaTotal = 500

    // Cálculo do progresso entre 0.0 (0%) e 1.0 (100%)
    val progresso = (valorGuardado.toFloat() / metaTotal.toFloat()).coerceIn(0f, 1f)
    val percentual = (progresso * 100).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Cabeçalho com Emoji (sem necessidade de dependência extra de ícones)
        Text(
            text = "🐷",
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Meu Cofrinho Digital",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B1F23)
        )

        Text(
            text = "Meta: Comprar Fone Bluetooth",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Cartão Principal (Destaque Visual)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "SALDO ACUMULADO",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "R$ $valorGuardado,00",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF2E7D32)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Barra de progresso visual
                LinearProgressIndicator(
                    progress = { progresso },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    color = Color(0xFF2E7D32),
                    trackColor = Color(0xFFE0E0E0)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Linha horizontal com informações nas extremidades
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$percentual% concluído",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Objetivo: R$ $metaTotal",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Row coloca os dois botões lado a lado com espaçamento igual
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { valorGuardado += 25 },
                modifier = Modifier
                    .weight(1f) // Ocupa 50% da largura
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
            ) {
                Text(text = "+ R$ 25", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { valorGuardado += 100 },
                modifier = Modifier
                    .weight(1f) // Ocupa os outros 50% da largura
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
            ) {
                Text(text = "+ R$ 100", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = { valorGuardado = 0 },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Reiniciar Cofrinho", color = Color.Gray)
        }
    }
}

// Permite visualizar o design no Android Studio sem abrir o emulador
@Preview(showBackground = true)
@Composable
fun PreviewTelaCofrinho() {
    MaterialTheme {
        TelaCofrinho()
    }
}