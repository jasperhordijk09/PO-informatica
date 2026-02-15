import os
from PIL import Image

base_path = "."   # <-- aanpassen

# Alleen deze mappen verwerken
allowed_folders = {"centrum-bg"}

image_extensions = {".jpg"}

for folder in os.listdir(base_path):
    folder_path = os.path.join(base_path, folder)

    # Alleen mappen die in allowed_folders staan
    if not os.path.isdir(folder_path) or folder not in allowed_folders:
        continue

    prefix = folder

    for filename in os.listdir(folder_path):
        name, ext = os.path.splitext(filename)
        ext = ext.lower()

        if ext not in image_extensions:
            continue

        old_path = os.path.join(folder_path, filename)
        new_name = f"{prefix}-img-{name}.png"
        new_path = os.path.join(folder_path, new_name)

        with Image.open(old_path) as img:
            img.save(new_path, "PNG")

        if ext != ".png":
            os.remove(old_path)

        print(f"{filename} → {new_name}")

print("Klaar!")
