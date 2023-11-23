package com.example.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;

public class OptimizeChunkGeneration {

    public static void optimizeChunkGeneration(Minecraft mc) {
        // Obtém a posição atual do jogador.
        BlockPos pos = mc.player.getPosition();

        // Cria uma lista de chunks que precisam ser gerados.
        List<BlockPos> chunksToGenerate = new ArrayList<>();

        // Para cada direção, verifica se o chunk adjacente precisa ser gerado.
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx != 0 || dy != 0 || dz != 0) {
                        BlockPos chunkPos = pos.add(dx, dy, dz);
                        if (!mc.world.isChunkGenerated(chunkPos)) {
                            chunksToGenerate.add(chunkPos);
                        }
                    }
                }
            }
        }

        // Gera os chunks na lista.
        for (BlockPos chunkPos : chunksToGenerate) {
            mc.world.generateChunk(chunkPos);
        }
    }

    public static void main(String[] args) {
        Minecraft mc = Minecraft.getInstance();
        optimizeChunkGeneration(mc);
    }
}
