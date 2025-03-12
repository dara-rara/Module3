package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicBoolean;


public class Task5 implements Task{
    private final Path sourceDir;
    private final Path targetDir;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private WatchService watchService;
    private Thread watchThread;

    public Task5(String sourceDir, String targetDir) {
        this.sourceDir = Paths.get(sourceDir);
        this.targetDir = Paths.get(targetDir);
    }

    @Override
    public void start() {
        if (running.get()) {
            System.out.println("Задача уже запущена.");
            return;
        }

        running.set(true);
        System.out.println("Задача синхронизации файлов запущена.");

        try {
            watchService = FileSystems.getDefault().newWatchService();
            sourceDir.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
            syncFolders();

            watchThread = new Thread(this::watchChanges);
            watchThread.start();
        } catch (IOException e) {
            System.out.println("Ошибка при запуске задачи: " + e.getMessage());
        }
    }

    @Override
    public void stop() {
        if (!running.get()) {
            System.out.println("Задача уже остановлена.");
            return;
        }

        running.set(false);
        System.out.println("Задача синхронизации файлов остановлена.");

        try {
            if (watchService != null) {
                watchService.close();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при остановке задачи: " + e.getMessage());
        }

        if (watchThread != null) {
            try {
                watchThread.join();
            } catch (InterruptedException e) {
                System.out.println("Поток был прерван: " + e.getMessage());
            }
        }
    }

    private void syncFolders() throws IOException {
        Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                var targetFile = targetDir.resolve(sourceDir.relativize(file));
                if (!Files.exists(targetFile) || Files.getLastModifiedTime(file).compareTo(Files.getLastModifiedTime(targetFile)) > 0) {
                    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                    System.out.println("Синхронизирован файл: " + file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void watchChanges() {
        while (running.get()) {
            try {
                var key = watchService.take();
                for (var event : key.pollEvents()) {
                    var kind = event.kind();

                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }

                    var changedFile = sourceDir.resolve((Path) event.context());
                    var targetFile = targetDir.resolve(sourceDir.relativize(changedFile));

                    if (kind == StandardWatchEventKinds.ENTRY_CREATE || kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        Files.copy(changedFile, targetFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                        System.out.println("Синхронизирован файл: " + changedFile);
                    }
                }
                key.reset();
            } catch (InterruptedException e) {
                System.out.println("Поток отслеживания изменений прерван: " + e.getMessage());
                break;
            } catch (ClosedWatchServiceException e) {
                System.out.println("WatchService закрыт, завершение потока.");
                break;
            } catch (IOException e) {
                System.out.println("Ошибка при синхронизации файла: " + e.getMessage());
            }
        }
    }
}
